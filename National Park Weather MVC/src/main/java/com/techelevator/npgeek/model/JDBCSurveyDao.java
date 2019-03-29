package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDao implements SurveyDao{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
    public JDBCSurveyDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

	@Override
	public Survey saveSurvey(Survey survey) {
		
		String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?,?)";
		survey.setSurveyId(getNextSurveyId());
		jdbcTemplate.update(sqlInsertSurvey, survey.getSurveyId(), survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		return survey;
		
	}
	
	private long getNextSurveyId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_surveyid')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new survey");
		}
	}

}
