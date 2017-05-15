package net.ignapzs.flightSearch.service.pricing;


import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;

import java.util.stream.Stream;

public interface PriceCalculator {

    default Stream<Flight> applyPricingRules(final FlightSearchRequest flightSearchRequest, final Stream<Flight> flights) {
        return flights.map(flightResponse -> apply(flightSearchRequest, flightResponse));
    }

    Flight apply(final FlightSearchRequest flightSearchRequest, final Flight flight);
}
