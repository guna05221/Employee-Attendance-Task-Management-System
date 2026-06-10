package com.eatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatm.dto.AddressDto;
import com.eatm.dto.EmployeeDto;
import com.eatm.dto.TaskDto;
import com.eatm.entity.Status;
import com.eatm.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/registerManager")
	public String registerEmployee(@ModelAttribute EmployeeDto employeeDto ,
			@ModelAttribute AddressDto addressDto) {
		return employeeService.registerManager(employeeDto, addressDto); 
	} 
	
	@RequestMapping("/login")
	public String loginEmployee(@RequestParam("loginEmail") String email, 
			@RequestParam("loginPassword") String password) {
		return employeeService.loginEmployee(email, password);
	}
	
	@RequestMapping("/addDeveloper")
	public String addDeveloper(@ModelAttribute EmployeeDto employeeDto, @ModelAttribute AddressDto addressDto) {
		return employeeService.addDeveloper(employeeDto, addressDto);
	}
	
	@RequestMapping("/createTask")
	public String createTask(@ModelAttribute TaskDto taskDto) {
		return employeeService.createTas(taskDto);
	}
	
	@RequestMapping("/assignTask")
	public String assignTask(@RequestParam("employeeId") int employeeId, @RequestParam("taskId") int taskId) {
		return employeeService.assignTask(employeeId, taskId);
	}
	
	
	@RequestMapping("/viewDetails")
	public String viewDetails(@RequestParam("email") String email) {
		return employeeService.viewDetails(email);
	}
	
	@RequestMapping("/deleteDeveloper")
	public String deleteDeveloper(@RequestParam("email") String email) {
		return employeeService.deleteDeveloper(email);
	}
	
	@RequestMapping("/logoutEmployee")
	public String logoutEmployee() {
		return employeeService.logoutEmployee();
	}
	
	@RequestMapping("/viewMyTask")
	public String viewMyTask() {
		return employeeService.viewMyTask();
	}
	
	@RequestMapping("/updateTaskStatus")
	public String updateTaskStatus(@RequestParam("taskId") int taskId, @RequestParam("Status") Status Status) {
		return employeeService.updateTaskStatus(taskId, Status);
	}
	
	@RequestMapping("/viewAttendance")
	public String viewAttendance() {
		return employeeService.viewAttendance();
	}
	
	@RequestMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("email") String email) {
		return employeeService.verifyEmail(email);
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(@RequestParam("password") String password) {
		return employeeService.changePassword(password);
	}
	
}
