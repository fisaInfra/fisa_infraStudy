package com.fisa.infra.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@GetMapping(value = "/account/login")
	public String login() {
		return "entire/account/login";
	}
}
