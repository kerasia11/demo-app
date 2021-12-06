package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.Countries;
import dao.CountryRegionGdp;
import dao.MaxGDP;

public class NationDB implements  NationInterface {
    public List<Countries> dbCountries() {
        List<Countries> countries = new ArrayList<>();
        List<Countries>  sortedList= new ArrayList<>();

        Connection conn =null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost[:3307]/[nation]", "root", "root");
            conn.setAutoCommit(false);
            String sql = "select name, area, country_code2 from countries";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            conn.commit();
            while (rs.next()){
                Countries c = new Countries();
                c.setName(rs.getString("name"));
                c.setArea(rs.getDouble("area"));
                c.setCountry_code2(rs.getString("country_code2"));
                countries.add(c);

            }
           sortedList = countries.stream().sorted().collect(Collectors.toList());
        }catch (SQLException e){
            System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }   finally{
            if (conn !=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
                }
            }
            return sortedList;
        }
    }

    @Override
    public List<String> dbSpokenLanguages(String country_code2) {
        List<String> languages = new ArrayList<>();
        Connection conn =null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost[:3307]/[nation]", "root", "root");
            conn.setAutoCommit(false);
        String sql = "select * from languages where language_id in (select language_id from country_languages where country_id in (select country_id from countries where country_code2=?))";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, country_code2);
        ResultSet rs = ps.executeQuery();
        conn.commit();
        while (rs.next()){
            languages.add(rs.getString("language"));
        }
    }catch (SQLException e){
        System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
    }catch (Exception e){
        e.printStackTrace();
    }   finally

    {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
            }
        }
        return languages;
    }
    }

    @Override
    public List<MaxGDP> dbMaxGdpPerCountry() {
        Connection conn =null;
        List<MaxGDP> maxGDPList= new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost[:3307]/[nation]", "root", "root");
            conn.setAutoCommit(false);
            String sql = "select c.name as name,c.country_code3 as country_code3, s.country_id, s.`year` as 'year', s.population as population, max(s.gdp) as maxGdp from countries c, country_stats s where c.country_id = s.country_id group by country_id order by max(gdp)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.commit();
            while (rs.next()){
                MaxGDP max = new MaxGDP();
                max.setName(rs.getString("name"));
                max.setCountry_code3(rs.getString("country_code3"));
                max.setYear(rs.getInt("year"));
                max.setPopulation(rs.getInt("population"));
                max.setMaxGdp(rs.getInt("maxGdp"));

                maxGDPList.add(max);
            }
        }catch (SQLException e){
            System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }   finally

        {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
                }
            }
            return maxGDPList;
        }
    }

    @Override
    public List<CountryRegionGdp> dbGdpPerRegion() {
        Connection conn =null;
        List<CountryRegionGdp> crgList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost[:3307]/[nation]", "root", "root");
            conn.setAutoCommit(false);
            String sql = "select c.name as countryName, r.name as regionName, con.name as continentName, st.`year`, st.population,st.gdp from countries c, regions r, continents con, country_stats st where c.country_id = st.country_id and c.region_id  =r.region_id and r.continent_id= con.continent_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.commit();
            while (rs.next()){
                CountryRegionGdp crg = new CountryRegionGdp();
                crg.setCountryName(rs.getString("countryName"));
                crg.setRegionName(rs.getString("regionName"));
                crg.setContinentName(rs.getString("continentName"));
                crg.setYear(rs.getInt("year"));
                crg.setPopulation(rs.getInt("population"));
                crg.setGdp(rs.getInt("gdp"));
                crgList.add(crg);
            }
        }catch (SQLException e){
            System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }   finally

        {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
                }
            }
            return crgList;
        }
    }

    @Override
    public List<CountryRegionGdp> dbGdpPerRegionByYear(int from, int to) {
        Connection conn =null;
        List<CountryRegionGdp> crgList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost[:3307]/[nation]", "root", "root");
            conn.setAutoCommit(false);
            String sql ="";
            if (from > 0 && to > 0){
                sql = "select c.name as countryName, r.name as regionName, con.name as continentName, st.`year`, st.population,st.gdp from countries c, regions r, continents con, country_stats st where c.country_id = st.country_id and c.region_id  =r.region_id and r.continent_id= con.continent_id and st.`year`>=? and st.`year`<=?";
            } else if (from > 0 && to < 0){
                sql = "select c.name as countryName, r.name as regionName, con.name as continentName, st.`year`, st.population,st.gdp from countries c, regions r, continents con, country_stats st where c.country_id = st.country_id and c.region_id  =r.region_id and r.continent_id= con.continent_id and st.`year`>=? ";
            }else if (from < 0 && to  > 0){
                sql = "select c.name as countryName, r.name as regionName, con.name as continentName, st.`year`, st.population,st.gdp from countries c, regions r, continents con, country_stats st where c.country_id = st.country_id and c.region_id  =r.region_id and r.continent_id= con.continent_id and st.`year`<=?";

            }
            PreparedStatement ps = conn.prepareStatement(sql);
            if (from > 0 && to > 0){
                ps.setInt(1,from);
                ps.setInt(2,to);
            } else if (from > 0 && to < 0){
                ps.setInt(1,from);
            }else if (from < 0 && to  > 0){
                ps.setInt(1,to);
            }
            ResultSet rs = ps.executeQuery();
            conn.commit();
            while (rs.next()){

                CountryRegionGdp crg = new CountryRegionGdp();
                crg.setCountryName(rs.getString("countryName"));
                crg.setRegionName(rs.getString("regionName"));
                crg.setContinentName(rs.getString("continentName"));
                crg.setYear(rs.getInt("year"));
                crg.setPopulation(rs.getInt("population"));
                crg.setGdp(rs.getInt("gdp"));
                crgList.add(crg);
            }
        }catch (SQLException e){
            System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }   finally

        {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.format("SQL State%s\n%s", e.getSQLState(), e.getMessage());
                }
            }
            return crgList;
        }
    }


}
