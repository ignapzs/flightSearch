package net.ignapzs.flightSearch.predicates;


import net.ignapzs.flightSearch.model.PricingRules;

import java.util.function.Predicate;

public class PricingRulesPredicates {

    public static Predicate<PricingRules> isBetween(int daysDepartureFrom) {
        return p -> p.getFrom() <= daysDepartureFrom && daysDepartureFrom <= p.getTo();
    }

}
