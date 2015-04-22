package com.cardekho.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private List<CityList> cityList = new ArrayList<CityList>();

    public List<CityList> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityList> cityList) {
        this.cityList = cityList;
    }

    public class CityList {

        @SerializedName("_id")
        private Integer Id;
        private String name;
        private String country;
        private Coord coord;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer Id) {
            this.Id = Id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

    }



    public class Coord {

        private Double lon;
        private Double lat;

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

    }

}