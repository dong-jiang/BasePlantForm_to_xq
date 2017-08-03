package com.abic.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.abic.security.domain.Emp;
import com.abic.security.service.EmpService;

@RestController
public class EmpController {

	/**
	 * GET 方式请求 /api/user/ 返回用户列表 • GET 方式请求 /api/user/1返回id为1的用户 • POST 方式请求
	 * /api/user/ 通过user对象的JSON 参数创建新的user对象 • PUT 方式请求 /api/user/3
	 * 更新id为3的发送json格式的用户对象 • DELETE 方式请求/api/user/4删除 ID为 4的user对象 • DELETE
	 * 方式请求/api/user/删除所有user
	 **/
	@Autowired
	EmpService empService;

	// -------------------Retrieve All
	// Emps--------------------------------------------------------

	@RequestMapping(value = "/emp/", method = RequestMethod.GET)
	public ResponseEntity<List<Emp>> listAllEmps() {
		List<Emp> emps = empService.findAllList();
		if (emps.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<Emp>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Emp>>(emps, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Emp--------------------------------------------------------

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Emp> getEmp(@PathVariable("id") int id) {
		System.out.println("Fetching Emp with id " + id);
		Emp emp = empService.findEmpById(id);
		if (emp == null) {
			System.out.println("Emp with id " + id + " not found");
			return new ResponseEntity<Emp>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Emp>(emp, HttpStatus.OK);
	}

	// -------------------Create a
	// Emp--------------------------------------------------------

	@RequestMapping(value = "/emp/", method = RequestMethod.POST)
	public ResponseEntity<Void> createEmp(@RequestBody Emp emp,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Emp " + emp.getEname());

		if (empService.isEmpExist(emp)) {
			System.out.println("A Emp with name " + emp.getEname()
					+ " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		empService.saveEmp(emp);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/emp/{id}")
				.buildAndExpand(emp.getEmpno()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Emp
	// --------------------------------------------------------

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Emp> updateEmp(@PathVariable("id") int id,
			@RequestBody Emp emp) {
		System.out.println("Updating Emp " + id);

		Emp currentEmp = empService.findEmpById(id);

		if (currentEmp == null) {
			System.out.println("Emp with id " + id + " not found");
			return new ResponseEntity<Emp>(HttpStatus.NOT_FOUND);
		}

		currentEmp.setEname(emp.getEname());
		currentEmp.setJob(emp.getJob());
		currentEmp.setSal(emp.getSal());

		empService.updateEmp(currentEmp);
		return new ResponseEntity<Emp>(currentEmp, HttpStatus.OK);
	}

	// ------------------- Delete a Emp
	// --------------------------------------------------------

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Emp> deleteEmp(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting Emp with id " + id);

		Emp emp = empService.findEmpById(id);
		if (emp == null) {
			System.out.println("Unable to delete. Emp with id " + id
					+ " not found");
			return new ResponseEntity<Emp>(HttpStatus.NOT_FOUND);
		}

		empService.deleteEmp(emp);
		return new ResponseEntity<Emp>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Emps
	// --------------------------------------------------------

	@RequestMapping(value = "/emp/", method = RequestMethod.DELETE)
	public ResponseEntity<Emp> deleteAllEmps() {
		System.out.println("Deleting All Emps");

		empService.deleteAllEmps();
		return new ResponseEntity<Emp>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 
	 * 详解：
	 * 
	 * @RestController :首先我们使用的是Spring 4的新注解 @RestController注解.
	 * 
	 *                 此注解避免了每个方法都要加上@ResponseBody注解。也就是说@RestController 自己戴上了
	 *                 @ResponseBody注解,看以看作是
	 * @Controller 和 @ResponseBody的结合体。
	 * @RequestBody : 如果方法参数被
	 *              @RequestBody注解，Spring将绑定HTTP请求体到那个参数上。如果那样做，Spring将根据请求中的ACCEPT或者
	 *              Content-Type header（私下）使用 HTTP Message converters
	 *              来将http请求体转化为domain对象。
	 * @ResponseBody :
	 *               如果方法加上了@ResponseBody注解，Spring返回值到响应体。如果这样做的话，Spring将根据请求中的
	 *               Content-Type header（私下）使用 HTTP Message converters
	 *               来将domain对象转换为响应体。
	 * 
	 * 
	 * 
	 *               ResponseEntity 是一个真实数据.它代表了整个 HTTP 响应（response）.
	 *               它的好处是你可以控制任何对象放到它内部。
	 * 
	 *               你可以指定状态码、头信息和响应体。它包含你想要构建HTTP Response 的信息。
	 * @PathVariable 此注解意味着一个方法参数应该绑定到一个url模板变量[在'{}'里的一个]中
	 * 
	 *               一般来说你，要实现REST API in Spring 4 需要了解@RestController ,
	 *               @RequestBody, ResponseEntity 和 @PathVariable 这些注解 .另外,
	 *               spring 也提供了一些支持类帮助你实现一些可定制化的东西。
	 * 
	 *               MediaType : 带着 @RequestMapping
	 *               注解,通过特殊的控制器方法你可以额外指定,MediaType来生产或者消耗。
	 * 
	 * 
	 * 
	 * */
}
