package net.ignapzs.flightSearch.model;


import java.util.Optional;

public class Airline {

    public final static double NO_INFANT_PRICE = Double.MIN_VALUE;
    private String code;
    private String name;
    private double infantPrice;

    public Airline(String iataCode, String name, Optional<Double> infantPrice) {
        this.code = iataCode;
        this.name = name;
        this.infantPrice = infantPrice.orElse(NO_INFANT_PRICE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getInfantPrice() {
        return infantPrice;
    }

}
