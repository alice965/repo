package org.itbank.app.controllers;

import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/apiservice")
public class ApiParkingController{
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/parking")
	public void api_ParkingHandle() {
		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/60/2/");
			Scanner sc = new Scanner(url.openStream(), "utf-8"); //인풋스트림이 열리는 것과 같음
			String str="";
			while(sc.hasNextLine()) {
				str+=sc.nextLine();
			}
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
