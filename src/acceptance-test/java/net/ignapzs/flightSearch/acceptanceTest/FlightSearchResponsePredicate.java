package net.ignapzs.flightSearch.acceptanceTest;


import net.ignapzs.flightSearch.dto.FlightSearchResponse;

import java.util.function.Predicate;

public class FlightSearchResponsePredicate {

    public static Predicate<FlightSearchResponse> isAirlineFlightCode(String airlineFlightCode) {
        return flightSearchResponse -> flightSearchResponse.getFlightCode().equalsIgnoreCase(airlineFlightCode);
    }

}
