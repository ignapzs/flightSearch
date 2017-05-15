package net.ignapzs.flightSearch.predicates;


import net.ignapzs.flightSearch.model.Airline;

import java.util.function.Predicate;

public class AirlinesPredicates {

    public static Predicate<Airline> hasAirlineChildFixedPrice(String code) {
        return airline -> airline.getCode().equalsIgnoreCase(code) && airline.getInfantPrice() != Airline.NO_INFANT_PRICE;
    }

}
