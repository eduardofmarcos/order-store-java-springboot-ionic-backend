package com.efm.orderstore.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.efm.orderstore.security.UserSS;

public class UserService {

	public static UserSS authenticatedUser() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
	
}
