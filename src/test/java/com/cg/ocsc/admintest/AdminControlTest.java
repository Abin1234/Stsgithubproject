package com.cg.ocsc.admintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.ocsc.OnlineCustomerServiceCenterApplication;
import com.cg.ocsc.entities.Department;
import com.cg.ocsc.entities.Operator;
import com.cg.ocsc.repositories.DepartmentDao;
import com.cg.ocsc.service.AdminService;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineCustomerServiceCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControlTest {
	
	@Autowired
	private DepartmentDao dnRepo;
	@Autowired
	private AdminService adminService;
     @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {

     }

    @Test
    public void testCreateDepartment() {
         Department dept = new Department();
         dept.setDepartmentId(2);
         dept.setDepartmentName("Programmer");
       //  adminService.addDepartment(dept);
        ResponseEntity<Department> postResponse = restTemplate.postForEntity(getRootUrl() + "/department/newDepartment", dept, Department.class);
        System.out.println("Department Name : "+dept.getDepartmentName());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    /* @Test
     public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();	//Represents an HTTP request or response entity, consisting of headers and body.
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees/all",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }*/

    @Test
    public void testGetDepartmentById() {
        Department dept = restTemplate.getForObject(getRootUrl() + "/department/2", Department.class);
        System.out.println("Department Name : "+dept.getDepartmentName());
        assertNotNull(dept);
    }

    @Test
    public void testUpdateDepartment() {
       int id = 2;
       
        Department dept = restTemplate.getForObject(getRootUrl() + "/department/byId/" + id, Department.class);
       // dept.setDepartmentId(2);
       dept.setDepartmentName("Developer");
      // adminService.modifyDepartment(dept);
        restTemplate.put(getRootUrl() + "/department/updateDepartment/" + id, dept);
        Department updatedDepartment = restTemplate.getForObject(getRootUrl() + "/department/byId/" + id, Department.class);
  assertNotNull(updatedDepartment);
	//assertEquals(dept.getDepartmentName(), updatedDepartment.getDepartmentName());
    }

    @Test
    public void testDeleteDepartment() {
         int id = 2;
         Department dept = restTemplate.getForObject(getRootUrl() + "/department/byId/" + id, Department.class);
//         assertNotNull(dept);
         restTemplate.delete(getRootUrl() + "/department/deleteEmployee/" + id);
         try {
              dept = restTemplate.getForObject(getRootUrl() + "/department/byId/" + id, Department.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
    
    // for  operator
    
   @Test
    public void testCreateOperator() {
    	Department dept=new Department();
    	dept.setDepartmentId(3);
    	dept.setDepartmentName("finance");
        Operator opt = new Operator();
        opt.setOperatorId(102);
         opt.setFirstName("Mark");
         opt.setLastName("smith");
         opt.setEmail("def@gmail.com");
         opt.setMobile("123456");
         opt.setCity("theni");
         opt.setDepartment(dept);
        ResponseEntity<Operator> postResponse = restTemplate.postForEntity(getRootUrl() + "/operator/newOperator", opt, Operator.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println("Operator Name : "+opt.getFirstName());
    }

     @Test
     public void testGetAllOperators() {
        HttpHeaders headers = new HttpHeaders();	//Represents an HTTP request or response entity, consisting of headers and body.
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/operators/all",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetOperatorById() {
        Operator opt = restTemplate.getForObject(getRootUrl() + "/operator/102", Operator.class);
        System.out.println("Operator Name : "+opt.getFirstName());
      assertNotNull(opt);
    }

    @Test
    public void testUpdateOperator() {
        int id = 102;
        Operator opt = restTemplate.getForObject(getRootUrl() + "/Operator/byId/" + id, Operator.class);
       opt.setFirstName("shruthi");
        restTemplate.put(getRootUrl() + "/operators/updateOperator/" + id, opt);
        Operator updatedOperator = restTemplate.getForObject(getRootUrl() + "/operator/byId" + id, Operator.class);
   assertNotNull(updatedOperator);
	//assertEquals(opt.getFirstName(), updatedOperator.getFirstName());
    }

    @Test
    public void testDeleteOperator() {
         int id = 102;
         Operator opt = restTemplate.getForObject(getRootUrl() + "/operator/byId/" + id, Operator.class);
//         assertNotNull(opt);
         restTemplate.delete(getRootUrl() + "/operator/deleteOperator/" + id);
         try {
              opt = restTemplate.getForObject(getRootUrl() + "/operator/byId/" + id, Operator.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }

}
