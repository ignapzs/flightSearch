package net.ignapzs.flightSearch.service;


import net.ignapzs.flightSearch.connector.locator.ConnectorServiceLocator;
import net.ignapzs.flightSearch.converters.ConverterServiceLocator;
import net.ignapzs.flightSearch.service.impl.FlightSearchServiceImpl;
import net.ignapzs.flightSearch.service.pricing.PassengerTypeImpl;
import net.ignapzs.flightSearch.service.pricing.PriceCalculator;
import net.ignapzs.flightSearch.service.pricing.PricingRuleImpl;

public enum ServiceLocator {

    INSTANCE;

    private final PriceCalculator pricingRulesCalculator;
    private final PriceCalculator passagerTypeCalculator;
    private final FlightSearchService flightSearchService;

    ServiceLocator() {
        pricingRulesCalculator = new PricingRuleImpl(ConnectorServiceLocator.INSTANCE.getPricingRulesConnector());
        passagerTypeCalculator = new PassengerTypeImpl(ConnectorServiceLocator.INSTANCE.getAirlinesConnector());
        flightSearchService = new FlightSearchServiceImpl(ConnectorServiceLocator.INSTANCE.getFlightConnector(), pricingRulesCalculator, passagerTypeCalculator, ConverterServiceLocator.INSTANCE.getFlightToFlightSearchResponseConverter());
    }

    public PriceCalculator getPricingRulesCalculator() {
        return this.pricingRulesCalculator;
    }

    public PriceCalculator getPassagerTypeCalculator() {
        return this.passagerTypeCalculator;
    }

    public FlightSearchService getFlightSearchService() {
        return this.flightSearchService;
    }

}
