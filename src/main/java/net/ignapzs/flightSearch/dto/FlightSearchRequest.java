package net.ignapzs.flightSearch.dto;


public class FlightSearchRequest {

    private String airportOriginCode;

    private String airportDestinationCode;

    private int departureFrom;

    private int adultAmount;

    private int childAmount;

    private int infantAmount;

    public FlightSearchRequest(String airportOriginCode, String airportDestinationCode, int departureFrom, int adultAmount, int childAmount, int infantAmount) {
        this.airportDestinationCode = airportDestinationCode;
        this.airportOriginCode = airportOriginCode;
        this.departureFrom = departureFrom;
        this.adultAmount = adultAmount;
        this.childAmount = childAmount;
        this.infantAmount = infantAmount;
    }

    public String getAirportOriginCode() {
        return airportOriginCode;
    }

    public String getAirportDestinationCode() {
        return airportDestinationCode;
    }

    public int getAdultAmount() {
        return adultAmount;
    }

    public int getChildAmount() {
        return childAmount;
    }

    public int getInfantAmount() {
        return infantAmount;
    }

    public int getDepartureFrom() {
        return departureFrom;
    }


}
