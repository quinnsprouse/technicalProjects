package com.techelevator.npgeek.model;

import java.util.List;

public interface ParkDao {
	public List<Park> getListOfParks();
	List<Park> getListOfParksWithSurvey();
}
