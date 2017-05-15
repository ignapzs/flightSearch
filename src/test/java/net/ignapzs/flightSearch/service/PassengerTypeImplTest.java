package net.ignapzs.flightSearch.service;

import net.ignapzs.flightSearch.connector.impl.AirlinesConnectorImpl;
import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Airline;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.service.pricing.PassengerTypeImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerTypeImplTest {

    private static final String ORIGIN = "Origin";
    private static final String DESTINATION = "Destination";
    private static final String AIRLINE = "Airline";
    private static final String AIRLINE_TO_TEST_IATA = "IB";
    private static final String AIRLINE_TO_TEST_NAME = "Iberia";
    private AirlinesConnectorImpl airlinesConnectorToTest;
    private FlightSearchRequest flightSearchRequestToTest;
    private Flight flightToTest;
    private Flight flightResultToTest;
    private PassengerTypeImpl passengerTypeToTest;
    private Airline airlineToTest;

    @Before
    public void initMembers() {
        this.airlinesConnectorToTest = mock(AirlinesConnectorImpl.class);
        this.flightSearchRequestToTest = mock(FlightSearchRequest.class);

        this.flightToTest = mock(Flight.class);
        when(flightToTest.getPrice()).thenReturn(100.0);
        when(flightToTest.getOrigin()).thenReturn(ORIGIN);
        when(flightToTest.getDestination()).thenReturn(DESTINATION);
        when(flightToTest.getAirline()).thenReturn(AIRLINE);

        this.passengerTypeToTest = new PassengerTypeImpl(airlinesConnectorToTest);
        this.airlineToTest = new Airline(AIRLINE_TO_TEST_IATA, AIRLINE_TO_TEST_NAME, Optional.of(10.0));
    }

    @After
    public void deleteMembers() {
        this.airlinesConnectorToTest = null;
        this.flightSearchRequestToTest = null;
        this.flightToTest = null;
        this.passengerTypeToTest = null;
        this.flightResultToTest = null;
        this.airlineToTest = null;
    }

    @Test
    public void applyAdultTest() {
        when(flightSearchRequestToTest.getAdultAmount()).thenReturn(2);
        when(flightSearchRequestToTest.getChildAmount()).thenReturn(0);
        when(flightSearchRequestToTest.getInfantAmount()).thenReturn(0);

        flightResultToTest = passengerTypeToTest.apply(flightSearchRequestToTest, flightToTest);

        assertTrue(flightResultToTest.getOrigin().equalsIgnoreCase(ORIGIN));
        assertTrue(flightResultToTest.getDestination().equalsIgnoreCase(DESTINATION));
        assertTrue(flightResultToTest.getAirline().equalsIgnoreCase(AIRLINE));
        assertTrue(flightResultToTest.getPrice() == 200.0);
    }

    @Test
    public void applyChildTest() {
        when(flightSearchRequestToTest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequestToTest.getChildAmount()).thenReturn(1);
        when(flightSearchRequestToTest.getInfantAmount()).thenReturn(0);

        flightResultToTest = passengerTypeToTest.apply(flightSearchRequestToTest, flightToTest);

        assertTrue(flightResultToTest.getOrigin().equalsIgnoreCase(ORIGIN));
        assertTrue(flightResultToTest.getDestination().equalsIgnoreCase(DESTINATION));
        assertTrue(flightResultToTest.getAirline().equalsIgnoreCase(AIRLINE));
        assertTrue(flightResultToTest.getPrice() == 67.0);
    }

    @Test
    public void applyInfantTest() {
        Stream<Airline> streamAirlinesMock = Stream.of(airlineToTest);

        when(airlinesConnectorToTest.filterEntities(Matchers.any())).thenReturn(streamAirlinesMock);
        when(flightSearchRequestToTest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequestToTest.getChildAmount()).thenReturn(0);
        when(flightSearchRequestToTest.getInfantAmount()).thenReturn(1);

        flightResultToTest = passengerTypeToTest.apply(flightSearchRequestToTest, flightToTest);

        assertTrue(flightResultToTest.getOrigin().equalsIgnoreCase(ORIGIN));
        assertTrue(flightResultToTest.getDestination().equalsIgnoreCase(DESTINATION));
        assertTrue(flightResultToTest.getAirline().equalsIgnoreCase(AIRLINE));
        assertTrue(flightResultToTest.getPrice() == 10.0);
    }
}