package db;

import java.util.List;

import dao.Countries;
import dao.CountryRegionGdp;
import dao.MaxGDP;

public interface NationInterface {
    List<Countries> dbCountries();

    List<String> dbSpokenLanguages(String country_code2);

    List<MaxGDP> dbMaxGdpPerCountry();

    List<CountryRegionGdp> dbGdpPerRegion();


    List<CountryRegionGdp> dbGdpPerRegionByYear(int from, int to);
}
