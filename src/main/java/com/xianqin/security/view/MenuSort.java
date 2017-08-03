package com.xianqin.security.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSort implements Comparable<MenuSort>
{
    private String id;
    private String parentId;
    private MenuVO menuVO;
    private Short orderNumber;
    private List<MenuSort> childrenMenuSort  = new ArrayList<MenuSort>();
    
    public MenuVO getMenuVO()
    {
        return menuVO;
    }

    public void setMenuVO(MenuVO menuVO)
    {
        this.menuVO = menuVO;
    }

    public Short getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(Short orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public List<MenuSort> getChildrenMenuSort()
    {
        return childrenMenuSort;
    }

    public void setChildrenMenuSort(List<MenuSort> childrenMenuSort)
    {
        this.childrenMenuSort = childrenMenuSort;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    
    @Override
    public int compareTo(MenuSort o)
    {
        int orderBy1 = this.orderNumber;
        int orderBy2 = o.orderNumber;
        return orderBy1 < orderBy2 ? -1 : (orderBy1 == orderBy2 ? 0 : 1);
    }
    
    public  void sortChildren(){
        Collections.sort(childrenMenuSort);
        for(MenuSort subMenu: childrenMenuSort){
            if (subMenu != null && subMenu.getChildrenMenuSort() != null) {
                subMenu.sortChildren();
            }
        }
    }
    
    public List<MenuVO> getMenuVOList(){
        List<MenuVO> menuVOList = new ArrayList<MenuVO>();
        menuVOList.add(menuVO);
        for(MenuSort menuSort:childrenMenuSort){
            menuVO.setIsleaf(false);
            int subMenuLevel = Integer.parseInt(menuVO.getLevel())+1;
            menuSort.getMenuVO().setLevel(String.valueOf(subMenuLevel));
            menuVOList.addAll(menuSort.getMenuVOList());
        }
        return menuVOList;
    }
    
    public static List<MenuVO> sortShowMenu(List<MenuVO> menuVOList){
      //组装Map数据,将所有菜单全部放到Map中，并用菜单ID作为Key来标记，方便后面的各节点寻找父节点。
        Map<String, MenuSort> dataMap = new HashMap<String, MenuSort>();//<{菜单ID},{菜单对象}>
        for (MenuVO menuVO:menuVOList) {
            MenuSort menuSort = new MenuSort();
            menuSort.setId(menuVO.getId());
            menuSort.setParentId(menuVO.getPid());
            menuSort.setMenuVO(menuVO);
            menuSort.setOrderNumber(menuVO.getOrderNum());
            dataMap.put(menuVO.getId(), menuSort);
        }
        //创建根节点
        MenuSort root = new MenuSort();
        MenuVO menuRoot = buildHomePage();
        root.setId(menuRoot.getId());
        root.setParentId(menuRoot.getPid());
        root.setOrderNumber(menuRoot.getOrderNum());
        root.setMenuVO(menuRoot);
        //组装树形结构，将子节点全部挂靠到自己的父节点
        for (Map.Entry<String, MenuSort> entry : dataMap.entrySet()) {
            MenuSort menu = entry.getValue();
            if (menu.getParentId().equals(root.getId())) {
                root.getChildrenMenuSort().add(menu);
            } else {
                dataMap.get(menu.getParentId()).getChildrenMenuSort().add(menu);
            }
        }
        //对多级树形结构进行“二叉树排序”
        root.sortChildren();
        //返回排序后的菜单节点
        List<MenuVO> returnList = root.getMenuVOList();
        returnList.get(0).setLevel("1");
        returnList.get(0).setIsleaf(true);
        return returnList;
    }
    
    public static MenuVO buildHomePage(){
        //{"id":"1","name":"首页","url":"main.html","pid":"1","icon":"icon-home","isleaf":true,"level":"1"},
        MenuVO menuVO = new MenuVO();
        menuVO.setId("0");
        menuVO.setIcon("icon-home");
        menuVO.setIsDefault("true");
        menuVO.setIsleaf(false);
        menuVO.setLevel("0");
        menuVO.setName("main");
        menuVO.setOrderNum((short)1);
        menuVO.setPid("0");
        menuVO.setTitle("首页");
        menuVO.setUrl("about:blank");
        return menuVO;
    }
}
