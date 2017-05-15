package net.ignapzs.flightSearch.service;


import net.ignapzs.flightSearch.connector.impl.FlightConnectorImpl;
import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.service.impl.FlightSearchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;
import net.ignapzs.flightSearch.converters.FlightToFlightSearchResponseConverter;
import net.ignapzs.flightSearch.dto.FlightSearchResponse;
import net.ignapzs.flightSearch.service.pricing.PassengerTypeImpl;
import net.ignapzs.flightSearch.service.pricing.PricingRuleImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceImplTest {

    @Test
    public void findFlightsTest() {
        Flight flight = new Flight("origin", "destination", "airline", 100.00);
        Stream<Flight> flights = Arrays.asList(flight).stream();

        FlightSearchResponse flightSearchResponse = new FlightSearchResponse("code", 100.00);
        List<FlightSearchResponse> FlightSearchResponseList = Arrays.asList(flightSearchResponse);

        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);

        PricingRuleImpl pricingRuleImpl = mock(PricingRuleImpl.class);
        PassengerTypeImpl passengerTypeImpl = mock(PassengerTypeImpl.class);
        FlightConnectorImpl flightConnectorImpl = mock(FlightConnectorImpl.class);
        FlightToFlightSearchResponseConverter flightToFlightSearchResponseConverter = mock(FlightToFlightSearchResponseConverter.class);

        when(flightConnectorImpl.filterEntities(Matchers.any())).thenReturn(flights);
        when(pricingRuleImpl.applyPricingRules(flightSearchRequest, flights)).thenReturn(flights);
        when(passengerTypeImpl.applyPricingRules(flightSearchRequest, flights)).thenReturn(flights);
        when(flightToFlightSearchResponseConverter.convertToList(flights)).thenReturn(FlightSearchResponseList);

        FlightSearchServiceImpl flightSearchServiceImpl = new FlightSearchServiceImpl(flightConnectorImpl, pricingRuleImpl, passengerTypeImpl, flightToFlightSearchResponseConverter);
        List<FlightSearchResponse> result = flightSearchServiceImpl.findFlights(flightSearchRequest);

        assertTrue(result.get(0).getFlightCode().equalsIgnoreCase("code"));
        assertTrue(result.get(0).getPrice() == 100.00);

        verify(flightConnectorImpl, times(1)).filterEntities(Matchers.any());
        verify(pricingRuleImpl, times(1)).applyPricingRules(flightSearchRequest, flights);
        verify(passengerTypeImpl, times(1)).applyPricingRules(flightSearchRequest, flights);
        verify(flightToFlightSearchResponseConverter, times(1)).convertToList(flights);
    }
}