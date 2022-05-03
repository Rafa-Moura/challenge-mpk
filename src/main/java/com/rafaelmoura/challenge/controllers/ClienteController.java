package com.rafaelmoura.challenge.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

	@RequestMapping("/teste")
	public String hello() {
		return "Hello World!";
	}

}
