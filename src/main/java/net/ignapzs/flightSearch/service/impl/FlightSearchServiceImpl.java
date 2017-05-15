package net.ignapzs.flightSearch.service.impl;


import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.connector.flight.FlightConnector;
import net.ignapzs.flightSearch.predicates.FlightPredicates;
import net.ignapzs.flightSearch.converters.Converter;
import net.ignapzs.flightSearch.dto.FlightSearchResponse;
import net.ignapzs.flightSearch.service.FlightSearchService;
import net.ignapzs.flightSearch.service.pricing.PriceCalculator;

import java.util.List;
import java.util.stream.Stream;

public class FlightSearchServiceImpl implements FlightSearchService {

    private FlightConnector flightConnector;

    private PriceCalculator pricingRulesCalculatorService;

    private PriceCalculator passengerTypeCalculatorService;

    private Converter<Flight, FlightSearchResponse> flightToFlightSearchResponseConverter;


    public FlightSearchServiceImpl(FlightConnector flightConnector, PriceCalculator pricingRulesCalculatorService, PriceCalculator passengerTypeCalculatorService, Converter<Flight, FlightSearchResponse> flightToFlightSearchResponseConverter) {
        this.flightConnector = flightConnector;
        this.pricingRulesCalculatorService = pricingRulesCalculatorService;
        this.passengerTypeCalculatorService = passengerTypeCalculatorService;
        this.flightToFlightSearchResponseConverter = flightToFlightSearchResponseConverter;
    }

    public List<FlightSearchResponse> findFlights(final FlightSearchRequest flightRequest) {

        final Stream<Flight> flights = flightConnector.filterEntities(FlightPredicates.match(flightRequest.getAirportOriginCode(), flightRequest.getAirportDestinationCode()));
        final Stream<Flight> flightsWithBasePrice = pricingRulesCalculatorService.applyPricingRules(flightRequest, flights);
        final Stream<Flight> flightsWithBasePriceAndPassengerType = passengerTypeCalculatorService.applyPricingRules(flightRequest, flightsWithBasePrice);

        return flightToFlightSearchResponseConverter.convertToList(flightsWithBasePriceAndPassengerType);
    }

}
