package net.ignapzs.flightSearch.service.pricing;


import net.ignapzs.flightSearch.connector.pricingrules.PricingRulesConnector;
import net.ignapzs.flightSearch.predicates.PricingRulesPredicates;
import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.model.PricingRules;

public class PricingRuleImpl implements PriceCalculator {

    private PricingRulesConnector pricingRulesConnector;

    public PricingRuleImpl(PricingRulesConnector pricingRulesConnector) {
        this.pricingRulesConnector = pricingRulesConnector;
    }

    @Override
    public Flight apply(final FlightSearchRequest flightSearchRequest, final Flight flight) {

        final int dayToDeparture = flightSearchRequest.getDepartureFrom();
        final PricingRules pricingRules = pricingRulesConnector.filterEntities(PricingRulesPredicates.isBetween(dayToDeparture)).findFirst().get();
        final double basePrice = (pricingRules.getPercentage() * flight.getPrice()) / 100;
        return new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), basePrice);
    }


}
