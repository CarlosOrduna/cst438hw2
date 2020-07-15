package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

    public void requestReservation( 
                   String cityName, 
                   String level, 
                   String email) {
		String msg  = "{\"cityName\": \""+ cityName + 
               "\" \"level\": \""+level+
               "\" \"email\": \""+email+"\"}" ;
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);
	}

    @Configuration
    public class ConfigPublisher {
    	@Bean
    	public FanoutExchange fanout() {
    		return new FanoutExchange("city-reservation");
    	}
    }

	
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
