package com.cg.ocsc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ocsc.entities.Department;
import com.cg.ocsc.entities.Operator;
import com.cg.ocsc.exceptions.DepartmentNotFoundException;
import com.cg.ocsc.exceptions.OperatorNotFoundException;
import com.cg.ocsc.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	@RequestMapping(value= "/department/add", method= RequestMethod.POST)
	public Department addDepartment(@RequestBody Department newdept) {       
	        Department dept=  adminService.addDepartment(newdept);
	        return dept;
	}
	@RequestMapping(value= "/department/delete/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Void> removeDepartment(@PathVariable int id) throws DepartmentNotFoundException {

	       Optional< Department> dept =  adminService.findDepartmentById(id) ;
	        if(!dept.isPresent())
	        	throw new DepartmentNotFoundException("Department not found");
	               //System.out.println("Could not find employee with id - " + id)
	        else   
	                 adminService.removeDepartment(id);
	        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value= "/department/update/{id}", method= RequestMethod.PUT)
    public  ResponseEntity<Object> modifyDepartment(@RequestBody Department upddept, @PathVariable int id) throws DepartmentNotFoundException {
        Optional<Department> dept= adminService.findDepartmentById(id);
        if(!dept.isPresent())
        	throw new DepartmentNotFoundException("Department not found");
        else
        {
			 upddept.setDepartmentId(id);
              adminService.modifyDepartment(upddept);
              return new ResponseEntity<>("Department is updated successfully", HttpStatus.OK);
        }
	}
	@RequestMapping(value="/departmentById/{id}", method=RequestMethod.GET)
	 public Department findDepartmentbyId(@PathVariable("id") int id) throws DepartmentNotFoundException {
	
	    Optional< Department> dept = adminService.findDepartmentById(id);
	    if (!dept.isPresent()) throw new DepartmentNotFoundException("Department not found");
	    // model.addAttribute(order);
	    else
	     return dept.get();
	}
	@RequestMapping(value= "/operator/add", method= RequestMethod.POST)
    	public Operator addOperator(@RequestBody Operator newop) {       
    	       return   adminService.addOperator(newop);
    	}
        @RequestMapping(value= "/operator/delete/{id}", method= RequestMethod.DELETE)
    	public ResponseEntity<Void> removeOperator(@PathVariable int id) throws OperatorNotFoundException {

    	        Optional<Operator> opt =  adminService.findOperatorById(id);
    	       if(!opt.isPresent())
    	        	throw new OperatorNotFoundException("Operator not found");
    	                //System.out.println("Could not find employee with id - " + id);
    	        else   
    	               adminService.removeOperator(id);
    	        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    	        
    	}
        @RequestMapping(value= "/operator/update/{id}", method= RequestMethod.PUT)
        public ResponseEntity<Object> modifyOperator(@RequestBody Operator updopt, @PathVariable int id) throws OperatorNotFoundException {
            Optional<Operator> opt= adminService.findOperatorById(id);
            if(!opt.isPresent())
            	throw new OperatorNotFoundException("Operator not found");
            else
            {
    			 updopt.setOperatorId(id);
                 adminService.modifyOperator(updopt);
                 return new ResponseEntity<>("Operator is updated successfully", HttpStatus.OK);
            }
    	}
        @RequestMapping(value= "/operatorById/{id}", method= RequestMethod.GET)
       public  Operator findOperatorById(@PathVariable int id) throws OperatorNotFoundException {
   Optional<Operator> opt=  adminService.findOperatorById(id);
             if(!opt.isPresent()) {
                 throw new OperatorNotFoundException("Operator not found");         // returns empty Employee object
             }
        	
         else
            	return opt.get();     // returns Employee object with data
        }
   
        @RequestMapping(value="/operator/all", method=RequestMethod.GET)
    	public ResponseEntity<List<Operator>> findAllOperators(){
        	List<Operator> listofalloperators= adminService.findAllOperators();
    		 return new ResponseEntity<List<Operator>>(listofalloperators,HttpStatus.OK);
    	}
        
        

}
