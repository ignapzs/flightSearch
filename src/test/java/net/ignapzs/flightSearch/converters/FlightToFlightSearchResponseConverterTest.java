package net.ignapzs.flightSearch.converters;


import net.ignapzs.flightSearch.dto.FlightSearchResponse;
import net.ignapzs.flightSearch.model.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightToFlightSearchResponseConverterTest {

    private final static  double PRICE = 100.00;
    private final static String AIRLINE = "IB1234";

    @Test
    public void applyTest() {
        Flight flight = mock(Flight.class);

        when(flight.getPrice()).thenReturn(PRICE);
        when(flight.getAirline()).thenReturn(AIRLINE);

        FlightToFlightSearchResponseConverter converter = new FlightToFlightSearchResponseConverter();
        FlightSearchResponse result = converter.apply(flight);

        assertTrue(result.getFlightCode().equalsIgnoreCase(AIRLINE));
        assertTrue(result.getPrice() == PRICE);
    }
}