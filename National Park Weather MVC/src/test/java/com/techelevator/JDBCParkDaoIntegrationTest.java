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


import com.techelevator.npgeek.model.JDBCParkDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;

public class JDBCParkDaoIntegrationTest {
	
	
	private static SingleConnectionDataSource dataSource;
	private ParkDao dao;
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
		dao = new JDBCParkDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void get_all_Parks() {
		
		// Arrange
		List<Park> parkList = dao.getListOfParks();
		int originalSize = parkList.size();	
		insertPark("YYY", "Awesome Park", 4000, "OH" , 50000, 15.0, 5, "Hot", 2000, 10000, "Hello World","Quinn Sprouse", "Great Park", 20, 10);
		// Act
		parkList = dao.getListOfParks();
		
		// Assert
		Assert.assertEquals("Wrong number of Parks returned", originalSize + 1, parkList.size());		
	}
	
	@Test
	public void get_all_Parks_and_survey_count() {
		
		// Arrange
		List<Park> parkList = dao.getListOfParksWithSurvey();
		int originalSize = parkList.size();	
		insertPark("YYY", "Awesome Park", 4000, "OH" , 50000, 15.0, 5, "Hot", 2000, 10000, "Hello World","Quinn Sprouse", "Great Park", 20, 10);
		// Act
		parkList = dao.getListOfParks();
		
		// Assert
		Assert.assertEquals("Wrong number of Parks returned", originalSize + 1, parkList.size());		
	}
	
	private void insertPark(String parkcode, String parkname, int acreage, String state, int elevationinfeet, double milesOfTrail, int numberofcampsites, String climate, int yearFounded, int annualVisitorCount, String inspirationalQuote, String inspirationalQuoteSource, String parkDescription, int entryFee, int numberOfAnimalSpecies) {
		String parkSql = "INSERT INTO park (parkcode, parkname, acreage, state, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(parkSql, parkcode, parkname, acreage, state, elevationinfeet, milesOfTrail, numberofcampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies);
		
	}

}
