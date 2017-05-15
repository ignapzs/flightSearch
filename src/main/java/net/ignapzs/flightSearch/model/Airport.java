package net.ignapzs.flightSearch.model;


public class Airport {

    private String code;

    private String city;

    public Airport(String iataCode, String city) {
        this.code = iataCode;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
