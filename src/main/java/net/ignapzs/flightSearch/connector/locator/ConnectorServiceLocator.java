package net.ignapzs.flightSearch.connector.locator;


import net.ignapzs.flightSearch.connector.airline.AirlinesConnector;
import net.ignapzs.flightSearch.connector.airport.AirportsConnector;
import net.ignapzs.flightSearch.connector.flight.FlightConnector;
import net.ignapzs.flightSearch.connector.impl.AirlinesConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.AirportsConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.FlightConnectorImpl;
import net.ignapzs.flightSearch.connector.impl.PricingRulesConnectorImpl;
import net.ignapzs.flightSearch.connector.pricingrules.PricingRulesConnector;

public enum ConnectorServiceLocator {

    INSTANCE;

    private final AirlinesConnector airlinesConnector;

    private final AirportsConnector airportsConnector;

    private final FlightConnector flightConnector;

    private final PricingRulesConnector pricingRulesConnector;

    ConnectorServiceLocator() {
        airlinesConnector = new AirlinesConnectorImpl();
        airportsConnector = new AirportsConnectorImpl();
        flightConnector = new FlightConnectorImpl();
        pricingRulesConnector = new PricingRulesConnectorImpl();
    }

    public AirlinesConnector getAirlinesConnector() {
        return this.airlinesConnector;
    }

    public AirportsConnector getAirportsConnector() {
        return this.airportsConnector;
    }

    public FlightConnector getFlightConnector() {
        return this.flightConnector;
    }

    public PricingRulesConnector getPricingRulesConnector() {
        return this.pricingRulesConnector;
    }
}
