package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.Countries;
import dao.CountryRegionGdp;
import dao.MaxGDP;
import db.NationDB;

@SpringBootApplication
public class DemoApplication {
	NationDB dbNation;
	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public List<Countries> retrieveCountries(){
		log.info("retrieveCountries -Receive Request");
		List<Countries> countries = dbNation.dbCountries();
		if (countries.isEmpty())
			log.error("No countries found");
		return countries;
	}

	public List<String> retrieveSpokenLanguages(String country_code2){
		log.info("retrieveSpokenLanguages -Receive Request for country code %s", country_code2);
		List<String> languages = dbNation.dbSpokenLanguages(country_code2);
		if (languages.isEmpty())
			log.error("No active languages for country %s", country_code2);

		return languages;
	}

	public List<MaxGDP> retrieveMaxGdpCountries(){
		log.info("retrieveMaxGdpCountries -Receive Request");
		List<MaxGDP> maxgdp = dbNation.dbMaxGdpPerCountry();
		if (maxgdp.isEmpty())
			log.error("Problem retrieving max GDP per country");

		return maxgdp;
	}

	public List<CountryRegionGdp> retrieveGdpPerRegion(){
		log.info("retrieveGdpPerRegion -Receive Request");
		List<CountryRegionGdp> countryRegionGdpList = dbNation.dbGdpPerRegion();
		if (countryRegionGdpList.isEmpty())
			log.error("Problem retrieving GDP per country/ region/ continent");
		return countryRegionGdpList;
	}


	public List<CountryRegionGdp> retrieveGdpByRegionName(String regionName) {
		log.info("retrieveGdpPerRegion -Receive Request for region name %s", regionName);
		List<CountryRegionGdp> countryRegionGdpList = dbNation.dbGdpPerRegion();
		if (countryRegionGdpList.isEmpty()){
			log.error("Problem retrieving GDP per country/ region/ continent");
			return countryRegionGdpList;
		}
		List<CountryRegionGdp> countryRegionGdpListByRegion =countryRegionGdpList.stream()
				.filter(countryRegionGdp ->
						(countryRegionGdp.getRegionName().equals(regionName)))
				.collect(Collectors.toList());

		return countryRegionGdpListByRegion;
	}

	public List<CountryRegionGdp> retrieveGdpByDate(int from, int to){
		log.info("retrieveGdpPerRegion -Receive Request from %s to %s", String.valueOf(from), String.valueOf(to));
		List<CountryRegionGdp> countryRegionGdpList = new ArrayList<>();
		if ((from < 0  && to< 0))
			return countryRegionGdpList;

		countryRegionGdpList = dbNation.dbGdpPerRegionByYear(from, to);
		if (countryRegionGdpList.isEmpty())
			log.error("Problem retrieving GDP per country/ region/ continent");
		return countryRegionGdpList;
	}
}
