package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.JDBCWeatherDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDao;

public class JDBCWeatherDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private WeatherDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Before
	public void setup() {
		dao = new JDBCWeatherDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void get_all_weather_for_park_code() {
		
		insertWeather("GNP", "Sunny", 6, 24, 56);
		
		// Arrange
		List<Weather> weatherList = dao.getListWeatherForParkCode("GNP");
		
		// Assert
		Assert.assertNotNull(weatherList);
		Assert.assertEquals(6, weatherList.get(weatherList.size()-1).getFiveDayForecastValue());
		Assert.assertEquals("GNP", weatherList.get(weatherList.size()-1).getParkCode());
	}
	
	@Test
	public void return_empty_list_for_park_code_that_doesnt_exist() {
		
		List<Weather> parkList = dao.getListWeatherForParkCode("ZZZZZZZZ");
		int sizeOfZero = parkList.size();	
		
		// Assert
		Assert.assertEquals(sizeOfZero, parkList.size());		
	}
	
	private void insertWeather(String parkCode, String forecast, int fiveDayForecastValue, int low, int high) {
		String weatherSql = "INSERT INTO weather (parkcode, forecast, fivedayforecastvalue, low, high) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(weatherSql, parkCode, forecast, fiveDayForecastValue, low, high);
		
	}

}
