package org.itbank.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/01")
	public void api_01Handle() {
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
	@RequestMapping("/02")
	public void api_02Handle(@RequestParam(defaultValue="1") String line) {
		System.out.println("/api/02");
		// Spring에서 제공하는 RestTemplate객체를 사용하지 않는다면,
		RestTemplate template = new RestTemplate();
		// get 방식으로 요청결과를 얻어오고 싶으면, 
		//String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/10/"+line+"/", 
		//				String.class);
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/{cnt}/{line}/", 
						String.class,5, line); //가변길이 인자로 설정하는 형태는 path에 설정한 이름이 중요하지 않음.
				//인자 설정을 맵으로 할 수 도 있는데, 이때는 설정했던 이름이 의미가 있음.
		System.out.println(resp);
		
		//ObjectMapper 
		// (JSON 형태 문자열==>객체 : readValue)
		// (객체==> JSON 형태 문자열: writeValueAsString)
		try {
			Map obj = mapper.readValue(resp, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// RestTemplate, 특정 경로로 요청을 보내고 거기서 응답을 받아볼 수 있음.
	// 이메일 발송에 html을 전송할 수 있음.
}
