package com.spring.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.notebook.entity.PrincipalDetails;
import com.spring.notebook.entity.Users;
import com.spring.notebook.repository.UserRepository;
import com.spring.notebook.service.UserDetailService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController  {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update")
	public String updatePage(Model model) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    String userEmail = authentication.getName();
	    model.addAttribute("email", userEmail);

	    log.info("User email: " + userEmail);
		
		return "userUpdate";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/updated")
	public String updateUser(HttpServletRequest request, Model model) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    String userEmail = authentication.getName();
		
		return "redirect:/update";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		
		return "redirect:/login";
	}
}
