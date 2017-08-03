package com.neusoft.common;

import java.net.URI;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.abic.security.domain.Emp;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xianqin.security.view.LoginVO;

public class RestMvcConfigTest {
	public static final String REST_SERVICE_URI = "http://localhost:8080/api";

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllEmps() {
		System.out.println("Testing listAllEmps API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> empsMap = restTemplate
				.getForObject(REST_SERVICE_URI + "/emp/", List.class);
		//"empno":7902,"ename":"FORD","job":"ANALYST","mgr":7566,"hiredate":376156800000,"sal":3000.0,"comm":null,"deptno":20
		if (empsMap != null) {
			for (LinkedHashMap<String, Object> map : empsMap) {
				System.out.println("Emp : id=" + map.get("empno") + ", Name="
						+ map.get("ename") + ", job=" + map.get("job")
						+ ", sal=" + map.get("sal")+ ", hiredate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((Long)map.get("hiredate"))));
				;
			}
		} else {
			System.out.println("No emp exist----------");
		}
	}

	/* GET */
	private static void getEmp() {
		System.out.println("Testing getEmp API----------");
		RestTemplate restTemplate = new RestTemplate();
		Emp emp = restTemplate.getForObject(REST_SERVICE_URI + "/emp/7900",
				Emp.class);
		System.out.println(emp);
	}

	/* POST */
	private static void createEmp() {
		System.out.println("Testing create Emp API----------");
		RestTemplate restTemplate = new RestTemplate();
		Emp emp = new Emp(3, "Sarah", new Date(), 134.00);
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/emp/",
				emp, Emp.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateEmp() {
		System.out.println("Testing update Emp API----------");
		RestTemplate restTemplate = new RestTemplate();
		Emp emp = new Emp(3, "Tomy", new Date(), 70000.00);
		restTemplate.put(REST_SERVICE_URI + "/emp/3", emp);
		System.out.println(emp);
	}

	/* DELETE */
	private static void deleteEmp() {
		System.out.println("Testing delete Emp API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/emp/3");
	}

	/* DELETE */
	private static void deleteAllEmps() {
		System.out.println("Testing all delete Emps API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/emp/");
	}

	   private static void login(){
	        System.out.println("Testing login API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        LoginVO loginvo = new LoginVO();
	        loginvo.setUsername("admin");
	        loginvo.setPassword("1");
	        loginvo.setGrant_type("2");
	        
	        try
            {
                System.err.println(new ObjectMapper().writeValueAsString(loginvo));
            }
            catch (JsonProcessingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	        LoginVO bakloginVO = restTemplate.postForObject(REST_SERVICE_URI + "/login/", loginvo, LoginVO.class,LoginVO.class);
	        System.err.println(bakloginVO.toString());
	        List<LinkedHashMap> menuVOList = restTemplate.postForObject(REST_SERVICE_URI + "/menu/", bakloginVO, List.class, LoginVO.class);
	        for(LinkedHashMap<String, Object> map:menuVOList){
	            System.out.println("Menu : id=" + map.get("id") + ", Name="
                        + map.get("name") + ", url=" + map.get("url")
                        + ", image=" + map.get("image")+ ", orderNum="
                        + map.get("orderNum") + ", isDefault=" + map.get("isDefault")
                        + ", parentId=" + map.get("parentId")+ ", title=" +  map.get("title"));
                ;
	        }
	    }
	   
	public static void main(String args[]) {
//		listAllEmps();
//		getEmp();
//		createEmp();
//		listAllEmps();
//		updateEmp();
//		listAllEmps();
//		deleteEmp();
//		listAllEmps();
//		deleteAllEmps();
//		listAllEmps();
		login();
	}
}
