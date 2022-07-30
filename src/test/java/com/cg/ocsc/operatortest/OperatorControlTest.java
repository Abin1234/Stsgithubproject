package com.cg.ocsc.operatortest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.ocsc.OnlineCustomerServiceCenterApplication;
import com.cg.ocsc.entities.Customer;
import com.cg.ocsc.entities.Issues;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineCustomerServiceCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperatorControlTest {
	
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
   public void testCreateCustomerIssue() {
       Issues issue = new Issues();
       issue.setIssueStatus("Closed");
       issue.setIssueId(1);
       issue.setIssueType("Active");
       ResponseEntity<Issues> postResponse = restTemplate.postForEntity(getRootUrl() + "/issue/create",issue, Issues.class);
       assertNotNull(postResponse);
       assertNotNull(postResponse.getBody());
   }

    @Test
    public void testUpdateCustomerIssue() {
    	 int id = 101;
    	 Issues issue= restTemplate.getForObject(getRootUrl() + "/issue/byId/"+id, Issues.class);
         issue.setDescription("okokok");
         issue.setIssueStatus("Closed");
         restTemplate.put(getRootUrl() + "/updateissue/"+id, issue);
         Issues updatedIssue = restTemplate.getForObject(getRootUrl() + "/issue/byId/"+id, Issues.class);
//         assertNotNull(updatedEmployee);
         System.out.println(issue.getIssueStatus());
         System.out.println(updatedIssue.getIssueStatus());
         assertEquals(issue.getIssueStatus(), updatedIssue.getIssueStatus());
     }

   @Test
   public void testFindCustomerById() {
       Customer customer = restTemplate.getForObject(getRootUrl() + "/fetchcustomerbyid/101", Customer.class);
       assertNotNull(customer);
   }
   
   @Test
   public void testFindCustomerByName() {
       Customer customer = restTemplate.getForObject(getRootUrl() + "/fetchcustomerbyname/shikhar", Customer.class);
       assertNotNull(customer);
   }
   
   @Test
   public void testFindCustomerByMail() {
       Customer customer = restTemplate.getForObject(getRootUrl() + "/fetchcustomerbymail/shikhar@takla", Customer.class);
       assertNotNull(customer);
   }
   

}
