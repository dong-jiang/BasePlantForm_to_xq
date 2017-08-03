package com.xianqin.dao;

import java.util.List;


import com.xianqin.domain.NfMenu;

/**
* <p>application name：xxxx</p>
* <p>application describing： this class handels xxxx</p>
* <p>copyright： Copyright &copy; 2017 东软版权所有
  </p>
* <p>company： neusoft</p>
* <p>time： 2017年6月20日 - 下午4:10:19</p>
*
* @author Administrator
* @version $Revision: 1.0 $
*/
public interface NfMenuDao
{
    public List<NfMenu> queryListByIdList(List<String> menuIdList);

}
