package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.FahrenheitToCelsius;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDao;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDao;


@Controller
@SessionAttributes("tempControl")
public class HomeController {
	
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private WeatherDao weatherDao;
	
	
	@RequestMapping("/home")
	public String showParkList(ModelMap map) {
		
		List<Park> parkList = parkDao.getListOfParks();
		
		map.addAttribute("parks", parkList);
		
		return "home";
	}
	
	
	@RequestMapping(path="/parkDetails", method=RequestMethod.GET)
	public String showDetailsList(ModelMap map, @RequestParam String parkCode) {
	
		List<Park> parkList = parkDao.getListOfParks();
		
		for(Park park : parkList) {
			if(park.getParkCode().equals(parkCode)) {
				map.addAttribute("selectedPark", park);
			}
		}
		if (!map.containsKey("tempControl")) {
			map.addAttribute("tempControl", new FahrenheitToCelsius());
		} 
		
		List<Weather> weather = weatherDao.getListWeatherForParkCode(parkCode);
		map.addAttribute("weatherList", weather);
		
		return "parkDetails";
	}
	@RequestMapping(path="/parkDetails", method=RequestMethod.POST)
	public String showDetailsListTemp(ModelMap map, @RequestParam String parkCode, @RequestParam boolean isCelsius) {
		
		FahrenheitToCelsius fahrenheitToCelsius;
	
		if (!map.containsKey("tempControl")) {
			map.addAttribute("tempControl", new FahrenheitToCelsius());
		}
		fahrenheitToCelsius = (FahrenheitToCelsius) map.get("tempControl");
		
		fahrenheitToCelsius.setCelsius(isCelsius);
		
		
		return "redirect:/parkDetails?parkCode=" + parkCode;
	}

	@RequestMapping(path="/addsurvey", method=RequestMethod.GET)
	public String showSurvey() {
		return "addsurvey";
	}
	
	@RequestMapping(path="/addsurvey", method=RequestMethod.POST)
	public String addSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "addsurvey"; 
		}
		
		attr.addFlashAttribute("survey", survey);
		
		surveyDao.saveSurvey(survey);
		
		return "redirect:/surveyList";
	}
	
	@RequestMapping(path="/surveyList", method=RequestMethod.GET)
	public String showSurveyList(ModelMap map) {
		List<Park> parkList = parkDao.getListOfParksWithSurvey();
		
		map.addAttribute("parks", parkList);
		
		return "surveyList";
	}
	

	

}
