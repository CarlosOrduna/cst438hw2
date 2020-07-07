package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		// TODO your code goes here
		// delete the following line
		List<City> cityData = cityRepository.findByName(cityName);
		CityInfo cityInfo = null;
		if (cityData.size() > 0) {
			City city = cityData.get(0);//get city
			
			Country countryData = countryRepository.findByCode(city.getCountryCode());//get country
			
			TempAndTime tempAndTime = weatherService.getTempAndTime(cityName); //get time
			Date date = new Date(tempAndTime.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
			String formattedDate = sdf.format(date);
			
			cityInfo = new CityInfo(city, countryData.getName(), tempAndTime.getTemp(), formattedDate); //modify object
		}
		return cityInfo;
	}
	
}
