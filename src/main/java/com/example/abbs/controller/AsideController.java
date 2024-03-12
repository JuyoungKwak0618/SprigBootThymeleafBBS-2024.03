package com.example.abbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.abbs.util.AsideUtil;
import com.example.abbs.util.ImageUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/aside")
public class AsideController {
	@Autowired private AsideUtil asideUtil;
	@Autowired private ImageUtil imageUtil;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	@ResponseBody
	@GetMapping("/stateMsg")
	public String changeStateMsg(String stateMsg, HttpSession session) {
		session.setAttribute("stateMsg", stateMsg);
		return "return message";
	}
	
	@PostMapping("/uploadProfileImage")
	public String uploadProfileImage(@RequestParam("profile") MultipartFile file,HttpSession session) {
		String filename = file.getOriginalFilename();
		session.setAttribute("profile",filename);
		return "fragments/base";
	}
		
	
	
	@ResponseBody
	@GetMapping("/weather")
	public String weather(HttpSession session) {
		String location = (String) session.getAttribute("location") + "청";
		String roadAddr = asideUtil.getRoadAddr(location);
		Map<String, String> map = asideUtil.getGeocode(roadAddr);
		String result = asideUtil.getWeatehr(map.get("lon"), map.get("lat"));
		return result;
	}
	
}
