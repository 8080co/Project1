package com.ex.reimbursementapi.services;

import com.ex.reimbursementapi.entities.EmailRequest;
import org.springframework.web.client.RestTemplate;

public class EmailRequester {

    private static final String POST_EMAIL_API ="http://app2:7000/email";
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * method to make the post request to the email api using restTemplate
     * @param toEmail
     * @param subject
     * @param body
     */


    public void sendRequest(String toEmail,String subject,String body){
        EmailRequest emailRequest = new EmailRequest(toEmail,subject,body);
        restTemplate.postForEntity(POST_EMAIL_API,emailRequest,null);

    }


}
