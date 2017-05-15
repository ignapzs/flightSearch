package net.ignapzs.flightSearch.connector;

import net.ignapzs.flightSearch.connector.impl.FlightConnectorImpl;
import net.ignapzs.flightSearch.model.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FlightConnectorImplTest {

    private Flight flightToTest;
    private FlightConnectorImpl flightConnectorToTest;

    @Before
    public void initMembers() {
        this.flightToTest = new Flight("origin", "destination", "airline", 100.0);
        this.flightConnectorToTest = new FlightConnectorImpl();
    }

    @After
    public void deleteMembers() {
        this.flightToTest = null;
        this.flightConnectorToTest = null;
    }

    @Test
    public void addEntityTest() {
        flightConnectorToTest.addEntity(flightToTest);

        assertTrue(flightConnectorToTest.filterEntities(p -> true).count() == 1);
    }

    @Test
    public void removeEntityTest() {
        flightConnectorToTest.addEntity(flightToTest);
        flightConnectorToTest.removeEntities(p -> true);

        assertTrue(flightConnectorToTest.filterEntities(p -> true).count() == 0);
    }

    @Test
    public void findEntityTest() {
        final int numberOfEntities = 3;
        for (int i = 0; i < numberOfEntities; i++) {
            flightConnectorToTest.addEntity(flightToTest);
        }
        assertTrue(flightConnectorToTest.filterEntities(p -> true).count() == numberOfEntities);
    }
}