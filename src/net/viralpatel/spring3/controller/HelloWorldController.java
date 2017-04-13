package net.viralpatel.spring3.controller;
 
import java.net.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HelloWorldController {
 
    @RequestMapping("/hello")
    public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	
    	System.out.println("---------"+request.getParameterValues("textId").toString());
    	System.out.println("---------"+request.getParameter("textId"));
    	System.out.println("---------"+request.getParameterMap().get("textId"));
 
        String message = "submitted value is "+request.getParameter("textId");
        return new ModelAndView("hello", "message", message);
    }
}