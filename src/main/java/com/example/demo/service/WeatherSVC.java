package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class WeatherSVC {

	public Map<Integer,String> getWeather() throws IOException, Exception {
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);

		// JSON데이터를 요청하는 URLstr을 만듭니다.
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		// 홈페이지에서 받은 키
		String serviceKey = "BsM6mi4Cj7L12sFjMlE0eL7WZJSmQsVvrlH9rvXCs5tF1y4P%2F03XTXIbD38XqzBW3FwuKbJ%2FCMEUYfPCjI9Mcg%3D%3D";
		String pageNo = "1";
		String numOfRows = "225"; // 한 페이지 결과 수
		String data_type = "JSON"; // 타입 xml, json 등등 ..
		String baseDate = tempDate; // "20200821"이런식으로 api에서 제공하는 형식 그대로 적으시면 됩니당.
		String baseTime = "0500"; // API 제공 시간을 입력하면 됨
		String nx = "60"; // 위도
		String ny = "120"; // 경도

		// 전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
				+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="
				+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
		urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8") + "&"); // 위도

		/* GET방식으로 전송해서 파라미터 받아오기 */
		URL url = new URL(urlBuilder.toString());
		// 어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
		System.out.println(url);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String result = sb.toString();
		System.out.println("결과: " + result);
		// 문자열을 JSON으로 파싱합니다. 마지막 배열형태로 저장된 데이터까지 파싱해냅니다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
		JSONObject parse_response = (JSONObject) jsonObj.get("response");
		JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
		JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
		// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.
		JSONArray parse_item = (JSONArray) parse_items.get("item");

		JSONObject obj;
		String category; // 기준 날짜와 기준시간을 VillageWeather 객체에 저장합니다.


		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		
		LocalTime seoulNow = LocalTime.now(ZoneId.of("Asia/Seoul"));
		int seoulHour = seoulNow.getHour();
		
		String time = time();


		
		String temperature = "";
		String wind_speed_EW = "";
		String wind_speed_SN = "";
		String wind_direction = "";
		String wind_speed ="";
		String weather = "";
		String precipitation_form = "";
		String precipitation_probability = "";
		String wave_height = "";
		String precipitation = "";
		String humidity = "";
		String snow = "";
		for (int i = 0; i < parse_item.size(); i++) {
			obj = (JSONObject) parse_item.get(i); // 해당 item을 가져옵니다.

			Object fcstValue = obj.get("fcstValue");
			Object fcstDate = obj.get("fcstDate");
			Object fcstTime = obj.get("fcstTime");
			category = (String) obj.get("category"); // item에서 카테고리를 검색해옵니다.
			// 검색한 카테고리와 일치하는 변수에 문자형으로 데이터를 저장합니다.
			// 데이터들이 형태가 달라 문자열로 통일해야 편합니다. 꺼내서 사용할때 다시변환하는게 좋습니다.

		
			if(fcstTime.equals(time)) {
				 if (category.equals("TMP")) {
						System.out.println(fcstValue);
						temperature = (String)fcstValue;
					}
				 if (category.equals("UUU")) {
						System.out.println("wind_speed_EW "+fcstValue);
						wind_speed_EW = (String)fcstValue;
					}
				 if (category.equals("UUU")) {
						System.out.println("wind_speed_SN "+fcstValue);
						wind_speed_SN = (String)fcstValue;
					}
				 if (category.equals("VEC")) {
						System.out.println("wind_direction "+fcstValue);
						wind_direction = (String)fcstValue;
					}
				 if (category.equals("WSD")) {
						System.out.println("wind_speed "+fcstValue);
						wind_speed = (String)fcstValue;
					}
				if (category.equals("SKY")) {
						System.out.println(fcstValue);
						if (fcstValue.equals("1")) {
							weather = "맑음";
						} else if (fcstValue.equals("3")) {
							weather = "구름 많음";
						} else if (fcstValue.equals("4")) {
							weather = "흐림";
						}
					}
				 if (category.equals("PTY")) {
						System.out.println("precipitation_form "+fcstValue);
						if(fcstValue.equals("0")) {
							precipitation_form = "강수 없음";
						} else if (fcstValue.equals("1")) {
							precipitation_form = "비";
						} else if (fcstValue.equals("2")) {
							precipitation_form = "비/눈";
						} else if (fcstValue.equals("3")) {
							precipitation_form = "눈";
						} else if (fcstValue.equals("4")) {
							precipitation_form = "소나기";
						}	
					}
				 if (category.equals("POP")) {
						System.out.println("precipitation_probability "+fcstValue);
						precipitation_probability = (String)fcstValue;
					}
				 if (category.equals("WAV")) {
						System.out.println("wave_height "+fcstValue);
						wave_height = (String)fcstValue;
					}
				 if (category.equals("PCP")) {
						System.out.println("precipitation "+fcstValue);
						precipitation = (String)fcstValue;
					}
				 if (category.equals("REH")) {
						System.out.println("humidity "+fcstValue);
						humidity = (String)fcstValue;
					}
				 if (category.equals("SNO")) {
						System.out.println("snow "+fcstValue);
						snow = (String)fcstValue;
					}
			}
				

		}
		Map<Integer,String> wMap = new HashMap<>();
		
		wMap.put(1, temperature);
		wMap.put(2, precipitation);
		wMap.put(3, wave_height);
		wMap.put(4, precipitation_form);
		wMap.put(5, precipitation_probability);
		wMap.put(6, wind_speed);
		wMap.put(7, wind_direction);
		wMap.put(8, wind_speed_SN);
		wMap.put(9, wind_speed_EW);
		wMap.put(10, weather);
		wMap.put(11, humidity);
		wMap.put(12, snow);
		
		String weatherInfo = "오늘 "+seoulHour+"시 "+weather+" 온도는 "+temperature+"도입니다.";
		wMap.put(13, weatherInfo);
		return wMap;
	}
	
	public String time() {
		String time = "";
		LocalTime seoulNow = LocalTime.now(ZoneId.of("Asia/Seoul"));
		int seoulHour = seoulNow.getHour();
		if (seoulHour == 0) {time = "0000";}
		else if (seoulHour == 1) {time ="0100";}
		else if (seoulHour == 2) {time = "0200";}
		else if (seoulHour == 3) {time = "0300";}
		else if (seoulHour == 4) {time = "0400";}
		else if (seoulHour == 5) {time = "0500";}
		else if (seoulHour == 6) {time = "0600";}
		else if (seoulHour == 7) {time = "0700";}
		else if (seoulHour == 8) {time = "0800";}
		else if (seoulHour == 9) {time = "0900";}
		else if (seoulHour == 10) {time = "1000";}
		else if (seoulHour == 11) {time = "1100";}
		else if (seoulHour == 12) {time = "1200";}
		else if (seoulHour == 13) {time = "1300";}
		else if (seoulHour == 14) {time = "1400";}
		else if (seoulHour == 15) {time = "1500";}
		else if (seoulHour == 16) {time = "1600";}
		else if (seoulHour == 17) {time = "1700";}
		else if (seoulHour == 18) {time = "1800";}
		else if (seoulHour == 19) {time = "1900";}
		else if (seoulHour == 20) {time = "2000";}
		else if (seoulHour == 21) {time = "2100";}
		else if (seoulHour == 22) {time = "2200";}
		else if (seoulHour == 23) {time = "2300";}
		return time;
	}
}
