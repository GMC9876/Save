package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.WeatherSVC;

@Controller
public class WeatherController {
	@Autowired
	private WeatherSVC wSvc;

	@GetMapping("/weather")
	public String getWeather(Model m) throws IOException, Exception {
		Map<Integer,String> weatherAll=wSvc.getWeather();
		
		String temperature = weatherAll.get(1);
		String precipitation = weatherAll.get(2);
		String wave_height = weatherAll.get(3);
		String precipitation_form = weatherAll.get(4);
		String precipitation_probability = weatherAll.get(5);
		String wind_speed = weatherAll.get(6);
		String wind_direction = weatherAll.get(7);
		String wind_speed_SN = weatherAll.get(8);
		String wind_speed_EW = weatherAll.get(9);
		String weather = weatherAll.get(10);
		String humidity = weatherAll.get(11);
		String snow = weatherAll.get(12);
		System.out.println(weatherAll);
		
		LocalTime seoulNow = LocalTime.now(ZoneId.of("Asia/Seoul"));
		int seoulHour = seoulNow.getHour();
		int seoulMinute = seoulNow.getMinute();
		
		m.addAttribute("temperature", temperature);
		m.addAttribute("precipitation", precipitation);
		m.addAttribute("wave_height", wave_height);
		m.addAttribute("precipitation_form", precipitation_form);
		m.addAttribute("precipitation_probability", precipitation_probability);
		m.addAttribute("wind_speed", wind_speed);
		m.addAttribute("wind_direction", wind_direction);
		m.addAttribute("wind_speed_SN", wind_speed_SN);
		m.addAttribute("wind_speed_EW", wind_speed_EW);
		m.addAttribute("humidity", humidity);
		m.addAttribute("snow", snow);

		m.addAttribute("weather", weather);
		m.addAttribute("hour", seoulHour);
		m.addAttribute("minute", seoulMinute);
		return "weather";
	}
	

}
