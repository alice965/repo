package org.itbank.app.controllers;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ApiParkingController {
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/api/parking/json")
	public ModelAndView parkListHandle(@RequestParam(defaultValue="") String location) {
		ModelAndView mav = new ModelAndView("t_expr");	//타일즈 연결
		mav.addObject("section", "api/parking");					//타일즈 section에 보낼 값 설정
		RestTemplate rt = new RestTemplate();					//템플릿 객체 생성
		String str = rt.getForObject(									//템플릿으로 api 정보를 가져옴. 스트링으로
				"http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/GetParkInfo/1/1000/"+location, String.class);
		try {
			Map obj = mapper.readValue(str, Map.class);			//스트링을 맵으로 값을 읽어 옴
			Map info = (Map)obj.get("GetParkInfo");				// GetParkInfo 키로 된 객체를 맵으로 가져옴.
			mav.addObject("data", info);								// data에 info를 세팅
			System.out.println("info : " + info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping("/api/parking/xml")
	public ModelAndView parkListXmlHandle(@RequestParam(defaultValue="") String location) {
		ModelAndView mav = new ModelAndView("t_expr");	
		mav.addObject("section", "api/parking");					
		RestTemplate rt = new RestTemplate();					//템플릿 객체 생성
		String str = rt.getForObject(									//템플릿으로 api 정보를 가져옴. 스트링으로
				"http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/xml/GetParkInfo/1/1000/"+location, String.class);
		Document doc =Jsoup.parse(str);
		Elements elms = doc.getElementsByTag("row");
		
		mav.addObject("data", elms);	
		return mav;
	}


}
