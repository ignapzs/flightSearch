package net.ignapzs.flightSearch.dto;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class FlightSearchResponse {

    private final static NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
    private String flightCode;
    private double price;

    public FlightSearchResponse(String flightCode, Double price) {
        this.flightCode = flightCode;
        this.price = price;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public double getPrice() {
        double returnedPrice = price;
        try {
            returnedPrice = (double) nf.parse(String.format("%.2f", price));
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return returnedPrice;
        }
    }

}
