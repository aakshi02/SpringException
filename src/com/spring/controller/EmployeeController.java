package com.spring.controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.exception.EmployeeNotFoundException;
import com.spring.model.Employee;

@Controller
public class EmployeeController {
	

	@RequestMapping(value="/emp/{empId}", method=RequestMethod.GET)
	public String getEmployee(@PathVariable("empId") int empId, Model model) throws Exception {
		if(empId == 1) {
			throw new EmployeeNotFoundException(empId);
		}else if(empId == 2) {
			throw new SQLException("sql exception occurred:" + empId);
		}else if(empId == 3) {
			throw new IOException("IO exception occurred:" + empId);
		} else if(empId == 10) {
			Employee e = new Employee(empId, "Aakshi");
			model.addAttribute("employee", e);
			return "home";
		} else {
			throw new Exception("Generic Exception : " + empId);
		}
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex ){
		ModelAndView model = new ModelAndView();
		model.addObject("exception", ex);
		model.addObject("url", request.getRequestURL());
		model.setViewName("error");
		return model;
	}
}
