package com.dattt.miniassignment.controller;

import com.dattt.miniassignment.dto.Users;
import com.dattt.miniassignment.service.CategoryService;
import com.dattt.miniassignment.service.UserService;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(){
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm")Users users, Model model, HttpSession session){
        String username = users.getUsername();
        String password = users.getPassword();
        Users a = userService.login(username, password);
        if(a != null && a.isAdmin() == false){
        	session.setAttribute("idUser", a.getId());
        	session.setAttribute("username", a.getUsername());
            return "redirect:/home";
        }else if(a != null && a.isAdmin() == true) {
        	session.setAttribute("idUser", a.getId());
        	session.setAttribute("username", a.getUsername());
        	return "redirect:/addProduct";
        }
        model.addAttribute("invalidCredentials", true);
        return "index";
    }
    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public String logout(ModelMap mm, HttpSession session) {
    	session.invalidate();
    	return "index";
    }
}
