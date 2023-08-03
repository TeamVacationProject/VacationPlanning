package com.organisation.vacationplanning.utility;

import org.thymeleaf.context.WebContext;

public class Notifier {
    public Notifier (WebContext ctx, String message, NotifTypes type){
        String messageNotifierVisible = "hidden";
        if(message == null || type == null){
            message = "";
        }else{
            messageNotifierVisible = "visible";
            ctx.setVariable("messageNotifierVisible", messageNotifierVisible);
            ctx.setVariable("message", message);
        }
    }
}
