package net.ignapzs.flightSearch.predicates;


import net.ignapzs.flightSearch.model.Flight;

import java.util.function.Predicate;

public class FlightPredicates {

    public static Predicate<Flight> match(String from, String to) {
        return f -> f.getOrigin().equalsIgnoreCase(from) && f.getDestination().equalsIgnoreCase(to);
    }

}
