package dao;

import java.util.Calendar;
import java.util.Date;

public class Countries {
    private Long country_id;
    private String name;
    private Double area;
    private Date national_day;
    private String country_code2;
    private String country_code3;
    private int region_id;

    public Countries(){}

    public Countries(Long  country_id, String name, Double area, Date national_day, String country_code2, String country_code3, int region_id) {
        this.country_id = country_id;
        this.name= name;
        this.area =area;
        this.national_day =national_day;
        this.country_code2 =country_code2;
        this.country_code3 =country_code3;
        this.region_id =region_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setNational_day(Date national_day) {
        this.national_day = national_day;
    }

    public void setCountry_code2(String country_code2) {
        this.country_code2 = country_code2;
    }

    public void setCountry_code3(String country_code3) {
        this.country_code3 = country_code3;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }



    public String getName() {
        return name;
    }

    public Double getArea() {
        return area;
    }

    public Date getNational_day() {
        return national_day;
    }

    public String getCountry_code2() {
        return country_code2;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public int getRegion_id() {
        return region_id;
    }

    public Long getCountryId() {
        return this.country_id;
    }

    public void setCountryId(Long country_id) {
        this.country_id = country_id;
    }

}
