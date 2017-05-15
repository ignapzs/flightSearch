package net.ignapzs.flightSearch.connector;

import net.ignapzs.flightSearch.connector.impl.AirlinesConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.AirportsConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.FlightConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.PricingRulesConnectorImpl;
import net.ignapzs.flightSearch.connector.locator.ConnectorServiceLocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectorServiceLocator.class})
public class ConnectorServiceLocatorTest {

    private AirlinesConnectorImpl airlinesConnectorToTest;
    private AirportsConnectorImpl airportsConnectorToTest;
    private FlightConnectorImpl flightConnectorToTest;
    private PricingRulesConnectorImpl pricingRulesConnectorToTest;
    private ConnectorServiceLocator connectorServiceLocatorToTest;

    @Before
    public void initMembers() {
        this.airlinesConnectorToTest = mock(AirlinesConnectorImpl.class);
        this.airportsConnectorToTest = mock(AirportsConnectorImpl.class);
        this.flightConnectorToTest = mock(FlightConnectorImpl.class);
        this.pricingRulesConnectorToTest = mock(PricingRulesConnectorImpl.class);
        this.connectorServiceLocatorToTest = mock(ConnectorServiceLocator.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocatorToTest);
    }

    @After
    public void deleteMembers(){
        this.airlinesConnectorToTest = null;
        this.airportsConnectorToTest = null;
        this.flightConnectorToTest = null;
        this.pricingRulesConnectorToTest = null;
        this.connectorServiceLocatorToTest = null;
    }

    @Test
    public void airlinesConnectorTest() {
        when(connectorServiceLocatorToTest.getAirlinesConnector()).thenReturn(airlinesConnectorToTest);

        ConnectorServiceLocator.INSTANCE.getAirlinesConnector();

        verify(connectorServiceLocatorToTest, times(1)).getAirlinesConnector();
    }

    @Test
    public void airportConnectorTest() {
        when(connectorServiceLocatorToTest.getAirportsConnector()).thenReturn(airportsConnectorToTest);

        ConnectorServiceLocator.INSTANCE.getAirportsConnector();

        verify(connectorServiceLocatorToTest, times(1)).getAirportsConnector();
    }

    @Test
    public void flightConnectorImplTest() {
        when(connectorServiceLocatorToTest.getFlightConnector()).thenReturn(flightConnectorToTest);

        ConnectorServiceLocator.INSTANCE.getFlightConnector();

        verify(connectorServiceLocatorToTest, times(1)).getFlightConnector();
    }

    @Test
    public void pricingRulesConnectorTest() {
        when(connectorServiceLocatorToTest.getPricingRulesConnector()).thenReturn(pricingRulesConnectorToTest);

        ConnectorServiceLocator.INSTANCE.getPricingRulesConnector();

        verify(connectorServiceLocatorToTest, times(1)).getPricingRulesConnector();
    }
}