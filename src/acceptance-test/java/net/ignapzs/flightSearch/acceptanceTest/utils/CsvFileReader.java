package net.ignapzs.flightSearch.acceptanceTest.utils;

import net.ignapzs.flightSearch.model.Airline;
import net.ignapzs.flightSearch.model.Airport;
import net.ignapzs.flightSearch.model.Flight;
import net.ignapzs.flightSearch.model.PricingRules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static net.ignapzs.flightSearch.acceptanceTest.utils.ClassPattern.inCaseOf;

public class CsvFileReader<E> {

    private final static Logger LOGGER = Logger.getLogger(CsvFileReader.class.getName());

    private static final String COMMA_DELIMITER = ",";

    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_CITY = 1;

    private static final int FLIGHT_ORIGIN = 0;
    private static final int FLIGHT_DESTINATION = 1;
    private static final int FLIGHT_AIRLINE = 2;
    private static final int FLIGHT_PRICE = 3;

    private static final int AIRLINE_CODE = 0;
    private static final int AIRLINE_NAME = 1;
    private static final int AIRLINE_PRICE = 2;

    private static final int PRICING_RULE_FROM = 0;
    private static final int PRICING_RULE_TO = 1;
    private static final int PRICING_RULE_PERCENTAGE = 2;

    public List<E> read(File fileName, E fakeInstanceClass, Class<E> type) {
        BufferedReader br = null;
        List<E> entities = new ArrayList<E>();
        try {
            String line;

            br = new BufferedReader(new FileReader(fileName));

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    PatternMatching patternMatching = new PatternMatching(
                            inCaseOf(Airport.class, airportCsvRecord -> new Airport(tokens[AIRPORT_CODE], tokens[AIRPORT_CITY])),
                            inCaseOf(Flight.class, flightCsvRecord -> new Flight(tokens[FLIGHT_ORIGIN], tokens[FLIGHT_DESTINATION], tokens[FLIGHT_AIRLINE], Integer.parseInt(tokens[FLIGHT_PRICE]))),
                            inCaseOf(Airline.class, airlineCsvRecord -> new Airline(tokens[AIRLINE_CODE], tokens[AIRLINE_NAME], Optional.of(Double.valueOf(tokens[AIRLINE_PRICE])))),
                            inCaseOf(PricingRules.class, pricingRulesCsvRecord -> new PricingRules(Integer.parseInt(tokens[PRICING_RULE_FROM]), Integer.parseInt(tokens[PRICING_RULE_TO]), Integer.parseInt(tokens[PRICING_RULE_PERCENTAGE])))
                    );
                    entities.add(type.cast(patternMatching.matchFor(fakeInstanceClass)));
                }
            }
        } catch (Exception e) {
            LOGGER.info("Exception: ");
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                LOGGER.info("IOException: ");
                e.printStackTrace();
            }
        }
        return entities;
    }
}