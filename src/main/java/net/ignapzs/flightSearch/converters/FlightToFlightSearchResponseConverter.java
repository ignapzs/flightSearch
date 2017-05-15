package net.ignapzs.flightSearch.converters;


import net.ignapzs.flightSearch.dto.FlightSearchResponse;
import net.ignapzs.flightSearch.model.Flight;

public class FlightToFlightSearchResponseConverter implements Converter<Flight, FlightSearchResponse> {

    @Override
    public FlightSearchResponse apply(Flight flight) {
        return new FlightSearchResponse(flight.getAirline(), flight.getPrice());
    }

}
