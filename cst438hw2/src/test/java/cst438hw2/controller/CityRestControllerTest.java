package cst438hw2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;
import cst438hw2.service.WeatherService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private WeatherService weatherService;
	
	@MockBean
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> jsonCityAttempt;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void getCityInfo() throws Exception {
		
		// TODO your code goes here
		//Country country = new Country("TST", "Test Country");
		
		City city = new City(1, "TestCity", "Test Country", "Test District", 100000);
		TempAndTime tempAndTime = new TempAndTime(100, 373, 0); 
		//long id, String name, String countryCode, String district, int population
		List<City> cities = new ArrayList<City>();
		cities.add(city);

		// create the stub calls and return data for weather service
		//  when the getWeather method is called with name parameter "TestCity", 
		//  the stub will return the given temp (in degrees Kelvin) and condition.
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(1, "TestCity", "TST", "Test Country", "Test District", 100000, 99.85, "9:00 pm"));
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(weatherService.getTempAndTime("TestCity")).willReturn(tempAndTime);

		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();
				

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult = new  CityInfo(1, "TestCity", "TST", "Test Country", "Test District", 100000, 99.85, "9:00 pm");
		// expected weather temp is in degrees F, not Kelvin.
		// expectedResult.setWeather(new CityWeather(80, "cloudy"));
		
		// compare actual return data with expected data
	    // MUST implement .equals( ) method for City class.
		assertThat(cityResult).isEqualTo(expectedResult);
	}
	
	@Test
	public void getCityNotFound() throws Exception {
		
		// TODO your code goes here
		//Country country = new Country("TST", "Test Country");
		
		City city = new City(1, "TestCity", "Test Country", "Test District", 100000);
		TempAndTime tempAndTime = new TempAndTime(100, 373, 0); 
		//long id, String name, String countryCode, String district, int population
		List<City> cities = new ArrayList<City>();
		cities.add(city);

		// create the stub calls and return data for weather service
		//  when the getWeather method is called with name parameter "TestCity", 
		//  the stub will return the given temp (in degrees Kelvin) and condition.
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(1, "TestCity", "TST", "Test Country", "Test District", 100000, 99.85, "9:00 pm"));
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(weatherService.getTempAndTime("TestCity")).willReturn(tempAndTime);

		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();
				

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult = new  CityInfo(10, "TestCityWrong", "TSTWrong", "Test CountryWrong", "Test DistrictWrong", 1, 9, "11:00 pm");
		// expected weather temp is in degrees F, not Kelvin.
		// expectedResult.setWeather(new CityWeather(80, "cloudy"));
		
		// compare actual return data with expected data
	    // MUST implement .equals( ) method for City class.
		assertFalse(cityResult.equals(expectedResult));
	}
	@Test
	public void getCityInfoMultiple() throws Exception {
		
		// TODO your code goes here
		//Country country = new Country("TST", "Test Country");
		
		City city = new City(1, "TestCity", "Test Country", "Test District", 100000);
		City city2 = new City(2, "TestCity2", "Test Country2", "Test District2", 200000);
		TempAndTime tempAndTime = new TempAndTime(100, 373, 0);  
		//long id, String name, String countryCode, String district, int population
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		cities.add(city2);

		// create the stub calls and return data for weather service
		//  when the getWeather method is called with name parameter "TestCity", 
		//  the stub will return the given temp (in degrees Kelvin) and condition.
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(1, "TestCity", "TST", "Test Country", "Test District", 100000, 99.85, "9:00 pm"));
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(weatherService.getTempAndTime("TestCity")).willReturn(tempAndTime);

		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity"))
				.andReturn().getResponse();
				

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult = new  CityInfo(1, "TestCity", "TST", "Test Country", "Test District", 100000, 99.85, "9:00 pm");
		// expected weather temp is in degrees F, not Kelvin.
		// expectedResult.setWeather(new CityWeather(80, "cloudy"));
		
		// compare actual return data with expected data
	    // MUST implement .equals( ) method for City class.
		assertThat(cityResult).isEqualTo(expectedResult);
	}
}
