package com.eatm.service;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import com.eatm.dao.AddressDao;
import com.eatm.dao.AttendanceDao;
import com.eatm.dao.EmployeeDao;
import com.eatm.dao.TaskDao;
import com.eatm.dto.AddressDto;
import com.eatm.dto.EmployeeDto;
import com.eatm.dto.TaskDto;
import com.eatm.entity.Address;
import com.eatm.entity.Attendance;
import com.eatm.entity.Employee;
import com.eatm.entity.Status;
import com.eatm.entity.Task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeService {

	@Autowired
	ModelMapper modelMapper; // use to convert dto to entity object

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	AddressDao addressDao;

	public String registerManager(EmployeeDto employeeDto, AddressDto addressDto) {
		// write logic to save address and employee in table

		// we need to convert all dto objects into entity object
		// also transfer data from dto to entity

		Address address = modelMapper.map(addressDto, Address.class);
		Employee employee = modelMapper.map(employeeDto, Employee.class);

		// set address to employee
		employee.setAddress(address);

		employee.setRole("manager");

		// save both object

		addressDao.saveAddress(address);
		employeeDao.saveEmployee(employee);

		return "success.jsp";
	}

	@Autowired
	Attendance attendance;

	@Autowired
	AttendanceDao attendanceDao;

	@Autowired
	HttpSession session;

	public String loginEmployee(String email, String password) {

		Employee dbEmployee = employeeDao.findEmployeeByEmailPassword(email, password);
		if (dbEmployee != null) {
			// login success - create attendance
			attendanceDao.saveAttendance(attendance);
			session.setAttribute("employee", dbEmployee);

			// map attendance to employee
			List<Attendance> attendanceList = dbEmployee.getAttendance();

			if (attendanceList == null) {
				attendanceList = new ArrayList<Attendance>();
			}
			attendanceList.add(attendance);
			dbEmployee.setAttendance(attendanceList);
			session.setAttribute("attendance", attendance);
			employeeDao.updateEmployee(dbEmployee);

			if (dbEmployee.getRole().equals("manager")) {
				return "manager.jsp";
			} else if (dbEmployee.getRole().equals("developer")) {
				return "developer.jsp";
			}
			return "success.jsp";

		} else {
			// login failure
			return "login.jsp";
		}
	}

	public String addDeveloper(EmployeeDto employeeDto, AddressDto addressDto) {

		// need to connvert dto object to entity object
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		Address address = modelMapper.map(addressDto, Address.class);
		addressDao.saveAddress(address);

		employee.setRole("developer");
		employee.setAddress(address);

		employeeDao.saveEmployee(employee);

		return "success.jsp";
	}

	@Autowired
	TaskDao taskDao;

	public String createTas(TaskDto taskDto) {
		Task task = modelMapper.map(taskDto, Task.class);

		task.setStatus(Status.CREATED);
		taskDao.saveTask(task);

		return "success.jsp";

	}

	public String assignTask(int employeeId, int taskId) {

		Employee employee = employeeDao.findEmployeeById(employeeId);
		Task task = taskDao.findTaskById(taskId);

		List<Task> taskList = employee.getTaskList();

		if (taskList == null) {
			taskList = new ArrayList<Task>();
		}

		task.setStatus(Status.ASSIGNED);

		taskList.add(task);

		employee.setTaskList(taskList);

		employeeDao.updateEmployee(employee);

		return "success.jsp";
	}

	@Autowired
	HttpServletRequest request;

	public String viewDetails(String email) {

		Employee employee = employeeDao.findEmployeeByEmail(email);

		request.setAttribute("employee", employee);

		return "displayDeveloperTask.jsp";

	}

	public String deleteDeveloper(String email) {

		Employee employee = employeeDao.findEmployeeByEmail(email);

		employeeDao.deleteEmployee(employee);

		return "success.jsp";
	}

	public String logoutEmployee() {

		Employee employee = (Employee) session.getAttribute("employee");
		
		if(employee!=null) {
			List<Attendance> attendanceList = employee.getAttendance();
		
			if(attendanceList !=null && !attendanceList.isEmpty()) {
				Attendance attendance = attendanceList.get(attendanceList.size() - 1);

				attendance.setLogoutTime(LocalDateTime.now());

				attendanceDao.updateAttendance(attendance);

			}
		}
	
		session.invalidate();

		return "login.jsp";
	}
	
	
	public String viewMyTask() {
		Employee employee = (Employee)session.getAttribute("employee");
		if(employee!=null) {
			Employee emp = employeeDao.findEmployeeByEmail(employee.getEmail());
		
			
			List<Task> taskList = emp.getTaskList();
			
			request.setAttribute("taskList", taskList);
		}
		
		return "viewMyTask.jsp";
	}
	
	public String updateTaskStatus(int taskId, Status status) {
		
		Employee employee = (Employee)session.getAttribute("employee");
		
		if(employee!=null) {
			List<Task> taskList = employee.getTaskList();
			
			if(taskList!=null && !taskList.isEmpty()) {
				for(Task task:taskList) {
					if(taskId == task.getTaskId()) {
						task.setStatus(status);
						taskDao.updateTask(task);
						break;
					}
				}
			}
				
		}
		
		return "success.jsp";
	}

	@Autowired
	HttpServletResponse response;
	
	public String viewAttendance() {
		Employee employee =(Employee)session.getAttribute("employee");
		
		if(employee!=null) {

			Employee dbEmployee = employeeDao.findEmployeeByEmail(employee.getEmail());
			
			List<Attendance> attendanceList = dbEmployee.getAttendance();
			
			
				
				request.setAttribute("list", attendanceList);
			
			
			
		}

		return "displayViewAttendance.jsp";
	}
	
	
	public String verifyEmail(String email){
		
		Employee employee = employeeDao.findEmployeeByEmail(email);
		
		session.setAttribute("forgot", employee);
		
		return "takeNewPassword.jsp";
	}
	
	public String changePassword(String password) {
		Employee employee = (Employee)session.getAttribute("forgot");
	
		employee.setPassword(password);
		
		employeeDao.updateEmployee(employee);
		
		return "success.jsp";
	}
	
}

//
//Address address = new Address();
//address.setCity(addressDto.getCity());
//address.setCountry(addressDto.getCountry());
//address.setHouseNumber(addressDto.getHouseNumber()); 
//address.setPincode(addressDto.getPincode());
//address.setState(addressDto.getState());
//address.setStreet(addressDto.getStreet());
