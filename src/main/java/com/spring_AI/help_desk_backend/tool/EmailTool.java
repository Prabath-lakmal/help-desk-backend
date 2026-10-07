package com.spring_AI.help_desk_backend.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class EmailTool {

    //send the email to support team regarding new ticket
    @Tool(description = "This tool help ot send email to support team regarding new ticket.")
    public void  sendEmailToSupportTeam(@ToolParam(description = "Email id associated with ticket for contact information.") String email, @ToolParam(description = "Short description of ticket summery.") String message){

        //sending email to support team
        System.out.println("going to send email to support team");
        System.out.printf("email id : "+email);
        System.out.printf("message : "+message);
    }
}
