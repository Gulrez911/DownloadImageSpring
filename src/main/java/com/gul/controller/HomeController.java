package com.gul.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String view() {
		return "home";
	}
	
	@GetMapping("/downloadFile")
	public void download(HttpServletRequest request , HttpServletResponse response) throws IOException  {
		File file = new File("D:\\Download\\demo.jpg");
		InputStream inputStream  = new BufferedInputStream(new FileInputStream(file));
		String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
		if(mimeType==null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"",file.getName()));
		FileCopyUtils.copy(inputStream,response.getOutputStream());
	}
	
}










