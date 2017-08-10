package com.roberto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by roberto on 02/08/17.
 */
@RestController
@RequestMapping("/status")
public class StatusController {

	@RequestMapping(method = RequestMethod.GET)
	public String status() {
		return "OK";
	}
}
