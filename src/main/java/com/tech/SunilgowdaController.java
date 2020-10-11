package com.tech;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.mbeans.UserMBean;
import org.hibernate.query.criteria.internal.expression.function.TrimFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SunilgowdaController {
	@Autowired
	SmtpMailSender smtpMailSender;
	
	@RequestMapping("/")
	public ModelAndView homepage() {
		return new ModelAndView("homepage");
	}
	
	@RequestMapping(value = "/sent", method = RequestMethod.POST)
	public String handleForm(HttpServletRequest request,SessionStatus status) throws MessagingException
	
	{
		String name=request.getParameter("name").toUpperCase().trim();
		String  email=request.getParameter("email").trim();
		String subject=request.getParameter("subject").trim();
		String message=request.getParameter("message").trim();
		
		System.out.println(name);
		System.out.println(email);
		System.out.println(subject);

		System.out.println(message);
		
		
		
		smtpMailSender.send(name,email,subject,message);

		
		
		status.setComplete();
        return "redirect:/success";
	}

	 @RequestMapping(value = "/success", method = RequestMethod.GET)
	    public String success(Model model) 
	    {
	         return "addSuccess";
	    }
}
