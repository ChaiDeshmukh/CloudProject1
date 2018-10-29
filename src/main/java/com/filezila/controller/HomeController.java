package com.filezila.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.filezila.model.FileDetails;
import com.filezila.model.User;
import com.filezila.service.FileService;
import com.filezila.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;

	private static String UPLOAD_FOLDER = "C://MS Stuff//";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(ModelMap model,HttpSession session) {
		User loggedUser=(User)session.getAttribute("loggedUser");
		String role=loggedUser.getRole();
		List<FileDetails> fileDetails=new ArrayList<>();
		if(!"admin".equalsIgnoreCase(role)){
		   fileDetails=fileService.findAllFileDetailsByEmail(loggedUser.getEmail());
		}else {
			fileDetails=fileService.findAllFileDetails();
		}
		model.addAttribute("fileDetails", fileDetails);
		return "home";
	}
	
	 @RequestMapping(value = "/upload", method = RequestMethod.GET)
		public String uploadPage(ModelMap model) {
			return "upload";
		}
	 
	 
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model,HttpSession session) {
		String id_token=(String)session.getAttribute("id_token");
		if(id_token!=null) {
		  try {
			GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(id_token);
			if(payLoad!=null) {
				payLoad.setExpirationTimeSeconds(1L);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		session.invalidate();
		
		return "login";
	}
	
	
	@RequestMapping(value = "/clogin", method = RequestMethod.GET)
	public String cloginPage(ModelMap model) {
		return "clogin";
	}
	
	
	@RequestMapping(value = "/clogin", method = RequestMethod.POST)
	public String cvalidateUser(@RequestParam("id_token") String id_token,
			ModelMap model, HttpSession session) {
		String email="";
		String name ="";
		try {
	            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(id_token);
	            
	             name = (String) payLoad.get("name");
	            email = payLoad.getEmail();
	            System.out.println("User name: " + name);
	            System.out.println("User email: " + email);
	           // session.setAttribute("userName", name);
	            session.setAttribute("id_token", id_token);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		User user = new User();
		user.setEmail(email);
		user.setFirstName(name);
		user.setRole("user");
		if (user == null) {
			model.addAttribute("loginError", "Sorry, user is not validated properly");
			return "redirect:/login";
		}
		session.setAttribute("loggedUser", user);
		return "redirect:/home";
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public String validateUser(@RequestParam("email") String email, 
			@RequestParam("password") String password,
			ModelMap model, HttpSession session) {
		
		
		User user = userService.validateUser(email, password);
		if (user == null) {
			model.addAttribute("loginError", "Sorry, user is not present please sign up");
			//return "redirect:/login";
		}
		session.setAttribute("loggedUser", user);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testPage(ModelMap model) {
		return "test";
	}
	
	

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUpPage(ModelMap model) {
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String addUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("password") String password, @RequestParam("email") String email, HttpSession session) {

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setEmail(email);

		userService.addUser(user);
		session.setAttribute("loggedUser", user);
		return "redirect:/home";
	}

}
