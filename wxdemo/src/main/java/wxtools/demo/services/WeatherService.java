package wxtools.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;

public class WeatherService {
	private Map<String, List<String>> weatherListDataMap;
	
	private WeatherWS weatherWS = null;
	private WeatherWSSoap weatherWSSoap = null;
	
	private static WeatherService instance = null;
	
	private WeatherService() {
		weatherListDataMap = new HashMap<String, List<String>>();
		weatherWS = new WeatherWS();
		weatherWSSoap = weatherWS.getWeatherWSSoap();
	}
	
	public static WeatherService getInstance() {
		if(instance == null) {
			synchronized (WeatherService.class) {
				WeatherService temp = instance;
				if(temp == null) {
					temp = new WeatherService();
					instance = temp;
				}
			}
		}
		
		return instance;
	}
	
	public List<String> getWeatherListData(String cityName) {
		List<String> result = null;
		
		if(weatherListDataMap.containsKey(cityName)) {
			result = weatherListDataMap.get(cityName);
		} else {
			ArrayOfString arrayOfString = weatherWSSoap.getWeather(cityName, "");
			result = arrayOfString.getString();
			
			for(int i=0; i < result.size(); i++) {
				if(result.get(i).contains("gif")) {
					result.set(i, "http://www.webxml.com.cn/images/weather");
				}
				System.out.println(i + " : " + result.get(i));
			}
			
			weatherListDataMap.put(cityName, result);
		}
		
		return result;
	}
}
