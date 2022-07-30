package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Department;
import com.capgemini.model.Operator;
import com.capgemini.service.AdminService;
import com.capgemini.exception.DepartmentNotFoundException;
import com.capgemini.exception.OperatorNotFoundException;



@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	@RequestMapping(value= "/department/add", method= RequestMethod.POST)
	public Boolean addDepartment(@RequestBody Department newdept) {       
	        return adminService.addDepartment(newdept);
	}
	@RequestMapping(value= "/department/delete/{id}", method= RequestMethod.DELETE)
	public Boolean removeDepartment(@PathVariable int id) throws DepartmentNotFoundException {

	        Department dept =  adminService.findDepartmentById(id);
	        if(dept.equals(null))
	        	throw new DepartmentNotFoundException();
	                //System.out.println("Could not find employee with id - " + id);
	        else   
	               return  adminService.removeDepartment(id);
	}
	
	@RequestMapping(value= "/department/update/{id}", method= RequestMethod.PUT)
    public Department modifyDepartment(@RequestBody Department upddept, @PathVariable int id) throws DepartmentNotFoundException {
        Department dept= adminService.findDepartmentById(id);
        if(dept.equals(null))
        	throw new DepartmentNotFoundException();
        else
        {
			 upddept.setDepartmentId(id);
             return adminService.modifyDepartment(upddept);
        }
	}
        @RequestMapping(value= "/department/{id}", method= RequestMethod.GET)
        public  Department findDepartmentById(@PathVariable int id) throws DepartmentNotFoundException {
    Department dept=  adminService.findDepartmentById(id);
               if(dept.equals(null)) {
                 throw new DepartmentNotFoundException();         // returns empty Employee object
            }
            else
                return dept;        // returns Employee object with data
        }
        @RequestMapping(value= "/operator/add", method= RequestMethod.POST)
    	public Boolean addOperator(@RequestBody Operator newop) {       
    	        return adminService.addOperator(newop);
    	}
        @RequestMapping(value= "/operator/delete/{id}", method= RequestMethod.DELETE)
    	public Boolean removeOperator(@PathVariable int id) throws OperatorNotFoundException {

    	        Operator opt =  adminService.findOperatorById(id);
    	        if(opt.equals(null))
    	        	throw new OperatorNotFoundException();
    	                //System.out.println("Could not find employee with id - " + id);
    	        else   
    	              return  adminService.removeOperator(id);
    	}
        @RequestMapping(value= "/operator/update/{id}", method= RequestMethod.PUT)
        public Operator modifyOperator(@RequestBody Operator updopt, @PathVariable int id) throws OperatorNotFoundException {
            Operator opt= adminService.findOperatorById(id);
            if(opt.equals(null))
            	throw new OperatorNotFoundException();
            else
            {
    			 updopt.setOperatorId(id);
                 return adminService.modifyOperator(updopt);
            }
    	}
        @RequestMapping(value= "/operator/{id}", method= RequestMethod.GET)
        public  Operator findOperatorById(@PathVariable int id) throws OperatorNotFoundException {
    Operator opt=  adminService.findOperatorById(id);
               if(opt.equals(null)) {
                 throw new OperatorNotFoundException();         // returns empty Employee object
            }
            else
                return opt;        // returns Employee object with data
        }
        @RequestMapping(value="/operator/all", method=RequestMethod.GET)
    	public List<Operator> findAllOperators(){
    		return adminService.findAllOperators();
    	}
        
        

}