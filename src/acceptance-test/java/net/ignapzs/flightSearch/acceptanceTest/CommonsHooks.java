package net.ignapzs.flightSearch.acceptanceTest;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.ignapzs.flightSearch.acceptanceTest.utils.CsvFileReader;
import net.ignapzs.flightSearch.connector.airline.AirlinesConnector;
import net.ignapzs.flightSearch.connector.airport.AirportsConnector;
import net.ignapzs.flightSearch.connector.flight.FlightConnector;
import net.ignapzs.flightSearch.connector.locator.ConnectorServiceLocator;
import net.ignapzs.flightSearch.connector.pricingrules.PricingRulesConnector;
import net.ignapzs.flightSearch.model.Airline;
import net.ignapzs.flightSearch.model.Airport;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.model.PricingRules;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CommonsHooks {

    private final static Logger LOGGER = Logger.getLogger(CommonsHooks.class.getName());
    private final static String AIRPORT_FILE_NAME = "airports.csv";
    private final static String FLIGHTS_FILE_NAME = "flights.csv";
    private final static String AIRLINES_FILE_NAME = "airlines.csv";
    private final static String PRICING_RULES_FILE_NAME = "pricingRules.csv";
    private final ClassLoader classLoader = getClass().getClassLoader();
    private AirlinesConnector airlinesConnector = ConnectorServiceLocator.INSTANCE.getAirlinesConnector();
    private AirportsConnector airPortsConnector = ConnectorServiceLocator.INSTANCE.getAirportsConnector();
    private FlightConnector flightConnector = ConnectorServiceLocator.INSTANCE.getFlightConnector();
    private PricingRulesConnector pricingRulesConnector = ConnectorServiceLocator.INSTANCE.getPricingRulesConnector();


    @Before("@loadAirports")
    public void loadAirports() {
        LOGGER.info("Loading Airports...");
        CsvFileReader<Airport> csvReader = new CsvFileReader();
        File airportCsv = new File(classLoader.getResource(AIRPORT_FILE_NAME).getFile());
        List<Airport> airports = csvReader.read(airportCsv, new Airport("FakeCode", "FakeCity"), Airport.class);
        airports.stream().forEach(airport -> airPortsConnector.addEntity(airport));
        LOGGER.info("Airports loaded.");
    }

    @Before("@loadFlights")
    public void loadFlights() {
        LOGGER.info("Loading flights...");
        CsvFileReader<Flight> csvReader = new CsvFileReader();
        File flightCsv = new File(classLoader.getResource(FLIGHTS_FILE_NAME).getFile());
        List<Flight> flights = csvReader.read(flightCsv, new Flight("FakeOrignin", "FakeDestination", "FakeAirline", 10), Flight.class);
        flights.stream().forEach(flight -> flightConnector.addEntity(flight));
        LOGGER.info("Flights loaded.");
    }

    @Before("@loadAirlines")
    public void loadAirlines() {
        LOGGER.info("Loading Airlines...");
        CsvFileReader<Airline> csvReader = new CsvFileReader();
        File airlineCsv = new File(classLoader.getResource(AIRLINES_FILE_NAME).getFile());
        List<Airline> airlines = csvReader.read(airlineCsv, new Airline("FakeIataCode", "FakeName", Optional.of(10.0)), Airline.class);
        airlines.stream().forEach(airline -> airlinesConnector.addEntity(airline));
        LOGGER.info("Airlines loaded.");
    }

    @Before("@loadPricingRules")
    public void loadPricingRules() {
        LOGGER.info("Loading pricing rules...");
        CsvFileReader<PricingRules> csvReader = new CsvFileReader();
        File pricingRulesCsv = new File(classLoader.getResource(PRICING_RULES_FILE_NAME).getFile());
        List<PricingRules> pricingRulesEntities = csvReader.read(pricingRulesCsv, new PricingRules(0, 0, 0), PricingRules.class);
        pricingRulesEntities.stream().forEach(p -> pricingRulesConnector.addEntity(p));
        LOGGER.info("Pricing rules loaded.");
    }

    @After("@CleanData")
    public void cleanData() {
        LOGGER.info("Cleaning all Data...");
        airlinesConnector.removeEntities(p -> true);
        airPortsConnector.removeEntities(p -> true);
        flightConnector.removeEntities(p -> true);
        LOGGER.info("All data cleaned.");
    }

}