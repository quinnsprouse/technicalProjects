package com.techelevator;

import static org.junit.Assert.assertNotNull;

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

import com.techelevator.npgeek.model.JDBCSurveyDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDao;

public class JDBCSurveyDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private SurveyDao dao;
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
		dao = new JDBCSurveyDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Test
	public void save_survey() {
		
		Survey survey = createSurvey("YYY", "quinnsprouse@gmail.com", "OH", "Active");
		long surveyId = survey.getSurveyId();
		dao.saveSurvey(survey);
		
		assertNotNull(createSurvey("YYY", "quinnsprouse@gmail.com", "OH", "Active"));
		Assert.assertNotEquals(surveyId, survey.getSurveyId());
	}
	
	private Survey createSurvey(String parkCode, String emailAddress, String state, String activityLevel) {
		Survey survey = new Survey();
		survey.setParkCode(parkCode);
		survey.setEmailAddress(emailAddress);
		survey.setState(state);
		survey.setActivityLevel(activityLevel);
		
		return survey;
	}

}
