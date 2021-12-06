package dao;

public class CountryRegionGdp {

    private String countryName;
    private String regionName;
    private String continentName;
    private int year;
    private int population;
    private int gdp;

    public CountryRegionGdp(){}

    public CountryRegionGdp(String countryName, String regionName, String continentName, int year, int population, int gdp){
        this.countryName =countryName;
        this.regionName =regionName;
        this.continentName= continentName;
        this.year =year;
        this.population= population;
        this.gdp = gdp;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getGdp() {
        return gdp;
    }

    public void setGdp(int gdp) {
        this.gdp = gdp;
    }
}
