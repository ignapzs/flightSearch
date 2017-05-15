package net.ignapzs.flightSearch.service.pricing;


import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.connector.airline.AirlinesConnector;
import net.ignapzs.flightSearch.predicates.AirlinesPredicates;
import net.ignapzs.flightSearch.model.Airline;

import java.util.stream.Stream;

public class PassengerTypeImpl implements PriceCalculator {

    private AirlinesConnector airlinesConnector;

    private final static int PERCENTAGE = 100 - 33; //100% of the official price - 33% discount

    public PassengerTypeImpl(AirlinesConnector airlinesConnector) {
        this.airlinesConnector = airlinesConnector;
    }

    @Override
    public Flight apply(final FlightSearchRequest flightSearchRequest, final Flight flight) {

        final int adultAmount = flightSearchRequest.getAdultAmount();
        final int childAmount = flightSearchRequest.getChildAmount();
        final int infantAmount = flightSearchRequest.getInfantAmount();

        final Flight flightAdultApplied = (adultAmount > 0) ? applyAdultPassengerTypeRules(adultAmount, flight) : new Flight("None", "None", "None", 0.0);
        final Flight flightChildApplied = (childAmount > 0) ? applyChildPassengerTypeRules(childAmount, flight) : new Flight("None", "None", "None", 0.0);
        final Flight flightInfantApplied = (infantAmount > 0) ? applyInfantPassengerTypeRules(infantAmount, flight) : new Flight("None", "None", "None", 0.0);

        final double totalPrice = flightAdultApplied.getPrice() + flightChildApplied.getPrice() + flightInfantApplied.getPrice();
        final Flight resultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), totalPrice);

        return resultFlight;
    }

    private Flight applyAdultPassengerTypeRules(final int adultAmount, final Flight flight) {

        final double adultTotalPrice = adultAmount * flight.getPrice();
        final Flight adultResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), adultTotalPrice);

        return adultResultFlight;
    }

    private Flight applyChildPassengerTypeRules(final int childAmount, final Flight flight) {
        final double childTotalPrice = childAmount * ((PERCENTAGE * flight.getPrice()) / 100);
        final Flight childResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), childTotalPrice);

        return childResultFlight;
    }

    private Flight applyInfantPassengerTypeRules(final int infantAmount, final Flight flight) {
        final Stream<Airline> airlineStream = airlinesConnector.filterEntities(AirlinesPredicates.hasAirlineChildFixedPrice(flight.getAirline().substring(0, 2)));
        final Airline airlineFixPrice = airlineStream.findFirst().get();
        final double infantTotalPrice = infantAmount * airlineFixPrice.getInfantPrice();
        final Flight infantResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), infantTotalPrice);

        return infantResultFlight;
    }
}
