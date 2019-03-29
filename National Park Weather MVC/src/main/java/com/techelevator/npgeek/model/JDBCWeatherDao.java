package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JDBCWeatherDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }
	
	@Override
	public List<Weather> getListWeatherForParkCode(String parkCode) {
		
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkCode);
		
		List<Weather> weathers = new ArrayList<Weather>();
		while(result.next()) {
			weathers.add(mapRowToWeather(result));
		}
		
		return weathers;
	}
	
	private Weather mapRowToWeather(SqlRowSet result) {
		
		Weather weather = new Weather();
		
		weather.setParkCode(result.getString("parkcode"));
		weather.setFiveDayForecastValue(result.getInt("fivedayforecastvalue"));
		weather.setLow(result.getInt("low"));
		weather.setHigh(result.getInt("high"));
		weather.setForecast(result.getString("forecast"));
		
		
		return weather;
	}

}
