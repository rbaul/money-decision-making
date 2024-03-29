package com.github.mdm.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FrontendStaticController {
    @GetMapping({"/", "/login", "/dashboard", "/activity-logs"})
    public String redirectToUI(HttpServletRequest request) {
		String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		return "redirect:/index.html?returnUrl=" + fullPath;
    }
}