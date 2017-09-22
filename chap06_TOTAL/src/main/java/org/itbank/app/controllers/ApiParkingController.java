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
		ModelAndView mav = new ModelAndView("t_expr");	//Ÿ���� ����
		mav.addObject("section", "api/parking");					//Ÿ���� section�� ���� �� ����
		RestTemplate rt = new RestTemplate();					//���ø� ��ü ����
		String str = rt.getForObject(									//���ø����� api ������ ������. ��Ʈ������
				"http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/GetParkInfo/1/1000/"+location, String.class);
		try {
			Map obj = mapper.readValue(str, Map.class);			//��Ʈ���� ������ ���� �о� ��
			Map info = (Map)obj.get("GetParkInfo");				// GetParkInfo Ű�� �� ��ü�� ������ ������.
			mav.addObject("data", info);								// data�� info�� ����
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
		RestTemplate rt = new RestTemplate();					//���ø� ��ü ����
		String str = rt.getForObject(									//���ø����� api ������ ������. ��Ʈ������
				"http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/xml/GetParkInfo/1/1000/"+location, String.class);
		Document doc =Jsoup.parse(str);
		Elements elms = doc.getElementsByTag("row");
		
		mav.addObject("data", elms);	
		return mav;
	}


}
