package net.ignapzs.flightSearch.converters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Converter<A, B> {

    default List<B> convertToList(final Stream<A> input) {
        return input.map(element -> this.apply(element)).collect(Collectors.toList());
    }

    B apply(A t);

}
