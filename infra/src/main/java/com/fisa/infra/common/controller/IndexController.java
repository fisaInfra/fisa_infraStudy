package com.fisa.infra.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {


	//웰컴 페이지인 index.html로 이동하는 컨트롤러 입니다.
	@GetMapping("/")
	public String indexForm(){

		return "index.html";

	}
}
