package net.ignapzs.flightSearch.connector;


import java.util.function.Predicate;
import java.util.stream.Stream;

public interface CommonConnector<E> {

    void addEntity(E entity);

    Stream<E> filterEntities(Predicate<E> predicate);

    void removeEntities(Predicate<E> predicate);

}
