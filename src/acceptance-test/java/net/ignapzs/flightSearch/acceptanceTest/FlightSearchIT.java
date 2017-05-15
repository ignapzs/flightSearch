package net.ignapzs.flightSearch.acceptanceTest;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.ignapzs.flightSearch.dto.FlightSearchRequest;
import net.ignapzs.flightSearch.dto.FlightSearchResponse;
import net.ignapzs.flightSearch.service.FlightSearchService;
import net.ignapzs.flightSearch.service.ServiceLocator;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FlightSearchIT {

    private FlightSearchService flightSearchService = ServiceLocator.INSTANCE.getFlightSearchService();

    private FlightSearchRequest flightSearchRequest;

    private FlightSearchResponse flightSearchResponse;

    private List<FlightSearchResponse> flightSearchResponseList = Collections.emptyList();

    @Given("^a route from \"([^\"]*)\" to \"([^\"]*)\" with (\\d+) days until departure, Adults: (\\d+), Children: (\\d+), and Infants: (\\d+)$")
    public void route(String airportOriginCode, String airportDestinationCode, int daysUntilDeparture, int adultAmount, int childAmount, int infantAmount) throws Throwable {
        flightSearchRequest = new FlightSearchRequest(airportOriginCode, airportDestinationCode, daysUntilDeparture, adultAmount, childAmount, infantAmount);
    }

    @When("^you perform a search$")
    public void search() throws Throwable {
        flightSearchResponseList = flightSearchService.findFlights(flightSearchRequest);
    }


    @Then("^you will get flight: \"([^\"]*)\", price: ([^\"]*), and the total number of flights retrieved is (\\d+)$")
    public void flight_list(String expectedFlightCode, String expectedPrice, int expectedAmountRetrieved) throws Throwable {
        final double dExpectedPrice = Double.valueOf(expectedPrice);

        assertTrue("Unexpected amount of flights. Found " + flightSearchResponseList.size() + " instead of " + expectedAmountRetrieved, flightSearchResponseList.size() == expectedAmountRetrieved);
        flightSearchResponse = flightSearchResponseList.stream().filter(FlightSearchResponsePredicate.isAirlineFlightCode(expectedFlightCode)).findFirst().orElse(new FlightSearchResponse("None", -1.0));

        assertTrue("Invalid expected price. Found " + flightSearchResponse.getPrice() + " instead of " + dExpectedPrice, flightSearchResponse.getPrice() == dExpectedPrice);
    }
}