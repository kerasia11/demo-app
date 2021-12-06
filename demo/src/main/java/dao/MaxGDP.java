package dao;

public class MaxGDP {

    private String name;
    private String country_code3;
    private int year;
    private int population;
    private int maxGdp;

    public MaxGDP(){}

    public MaxGDP(String name, String country_code3, int year, int population, int maxGdp){
        this.name = name;
        this.country_code3= country_code3;
        this.year =year;
        this.population=population;
        this.maxGdp=maxGdp;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public void setCountry_code3(String country_code3) {
        this.country_code3 = country_code3;
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

    public int getMaxGdp() {
        return maxGdp;
    }

    public void setMaxGdp(int maxGdp) {
        this.maxGdp = maxGdp;
    }
}
