package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDao implements ParkDao{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCParkDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

	@Override
	public List<Park> getListOfParks() {
		
		String sql = "SELECT * FROM park ORDER BY parkname ASC";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		List<Park> parks = new ArrayList<Park>();
		while(result.next()) {
			parks.add(mapRowToPark(result));
		}
		
		return parks;
	}
	@Override
	public List<Park> getListOfParksWithSurvey() {
		
		String sql = "SELECT p.parkcode, p.parkname, p.acreage, p.state, p.elevationinfeet, p.milesoftrail, p.numberofcampsites, p.climate, p.yearfounded, p.annualvisitorcount, p.inspirationalquote, p.inspirationalquotesource, p.parkdescription, p.entryfee, p.numberofanimalspecies, count(activitylevel) AS surveycount \n" + 
				"FROM survey_result sr  \n" + 
				"RIGHT JOIN park p ON p.parkcode = sr.parkcode \n" + 
				"GROUP BY sr.parkcode, p.parkcode, p.acreage, p.state, p.elevationinfeet, p.milesoftrail, p.numberofcampsites, p.climate, p.yearfounded, p.annualvisitorcount, p.inspirationalquote, p.inspirationalquotesource, p.parkdescription, p.entryfee, p.numberofanimalspecies\n" + 
				"ORDER BY surveycount DESC, p.parkcode";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		List<Park> parks = new ArrayList<Park>();
		while(result.next()) {
			parks.add(mapRowToParkSurvey(result));
		}
		
		return parks;
	}
	
	private Park mapRowToParkSurvey(SqlRowSet result) {
		
		Park park = new Park();
		
		park.setParkCode(result.getString("parkcode"));
		park.setParkName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getInt("acreage"));
		park.setElevationInFeet(result.getInt("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setNumberOfCampSites(result.getInt("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getInt("yearfounded"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
		park.setCount(result.getInt("surveycount"));
		return park;
	}
	private Park mapRowToPark(SqlRowSet result) {
		
		Park park = new Park();
		
		park.setParkCode(result.getString("parkcode"));
		park.setParkName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getInt("acreage"));
		park.setElevationInFeet(result.getInt("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setNumberOfCampSites(result.getInt("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getInt("yearfounded"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
		return park;
	}
}
