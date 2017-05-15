package net.ignapzs.flightSearch.service;


import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.dto.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {

    List<FlightSearchResponse> findFlights(FlightSearchRequest flightRequest);

}
