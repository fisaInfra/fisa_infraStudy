package com.fisa.infra.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@GetMapping(value = "/account/login")
	public String login() {
		return "entire/account/login";
	}
}
