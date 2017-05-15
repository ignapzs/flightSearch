package net.ignapzs.flightSearch.service;

import net.ignapzs.flightSearch.connector.impl.PricingRulesConnectorImpl;
import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.model.PricingRules;
import net.ignapzs.flightSearch.service.pricing.PricingRuleImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricingRuleImplTest {

    @Test
    public void applyTest() {
        PricingRules p = new PricingRules(16, 30, 100);
        Stream<PricingRules> streamPricingRulesMock = Arrays.asList(p).stream();

        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        Flight flightMock = mock(Flight.class);
        PricingRulesConnectorImpl pricingRulesConnectorImpl = mock(PricingRulesConnectorImpl.class);

        PowerMockito.when(pricingRulesConnectorImpl.filterEntities(Matchers.any())).thenReturn(streamPricingRulesMock);

        when(flightSearchRequest.getDepartureFrom()).thenReturn(19);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("destination");
        when(flightMock.getAirline()).thenReturn("airline");

        PricingRuleImpl pricingRuleImpl = new PricingRuleImpl(pricingRulesConnectorImpl);
        Flight result = pricingRuleImpl.apply(flightSearchRequest, flightMock);

        assertTrue(result.getAirline().equalsIgnoreCase("airline"));
        assertTrue(result.getDestination().equalsIgnoreCase("destination"));
        assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
        assertTrue(result.getPrice() == 100.0);

        verify(pricingRulesConnectorImpl, times(1)).filterEntities(Matchers.any());
    }
}