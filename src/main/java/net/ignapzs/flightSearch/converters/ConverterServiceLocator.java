package net.ignapzs.flightSearch.converters;


public enum ConverterServiceLocator {

    INSTANCE;

    private final Converter flightToFlightSearchResponseConverter;

    ConverterServiceLocator() {
        flightToFlightSearchResponseConverter = new FlightToFlightSearchResponseConverter();
    }

    public Converter getFlightToFlightSearchResponseConverter() {
        return this.flightToFlightSearchResponseConverter;
    }

}
