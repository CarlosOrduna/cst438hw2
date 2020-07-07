package cst438hw2.service;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	// This method is executed before each test
	@BeforeEach
     public void setUpEach() {
    	MockitoAnnotations.initMocks( this);
    	
    }
	
	@Test
	public void testCityFound() throws Exception {
		// TODO 
		//Country country = new Country("TST", "Test Country");
	}
	
	@Test 
	public void  testCityNotFound() {
		// TODO 
	}
	
	@Test 
	public void  testCityMultiple() {
		// TODO 
		
	}

}
