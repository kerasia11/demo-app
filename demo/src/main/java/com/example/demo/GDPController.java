package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import dao.Countries;
import dao.CountryRegionGdp;
import dao.MaxGDP;
import io.swagger.annotations.Api;

@Api
@Controller
@RequestMapping("/gdp/data")
public class GDPController {

    DemoApplication demoapp = new DemoApplication();

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public @ResponseBody
    List<Countries> retrieveCountries() {
        return demoapp.retrieveCountries();
    }

    @RequestMapping(value = "/countries/languages/{country_code2}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> retrieveSpokenLanguages(@PathVariable String country_code2) {
        return demoapp.retrieveSpokenLanguages(country_code2);
    }

    @RequestMapping(value = "/countries/maxGdp", method = RequestMethod.GET)
    public @ResponseBody
    List<MaxGDP> retrieveMaxGdpCountries() {
        return demoapp.retrieveMaxGdpCountries();
    }

    @RequestMapping(value = "/countries/regions/gdp", method = RequestMethod.GET)
    public @ResponseBody
    List<CountryRegionGdp> retrieveGdpPerRegion() {
        return demoapp.retrieveGdpPerRegion();
    }

    @RequestMapping(value = "/countries/regions/gdp/{regionName}", method = RequestMethod.GET)
    public @ResponseBody
    List<CountryRegionGdp> retrieveGdpPerRegionByName(@PathVariable String regionName)  {
        return demoapp.retrieveGdpByRegionName(regionName);
    }

    @RequestMapping(value = "/countries/regions/gdp/", method = RequestMethod.POST)
    public @ResponseBody
    List<CountryRegionGdp> retrieveGdpByDate(@RequestParam(value = "from", required=false, defaultValue = "0") Integer from,@RequestParam(value = "to", required=false, defaultValue = "0") Integer to)  {
        return demoapp.retrieveGdpByDate(from, to);
    }
}
