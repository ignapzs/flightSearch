package net.ignapzs.flightSearch.acceptanceTest.utils;


public interface Pattern {

    boolean matches(Object value);

    Object apply(Object value);
}
