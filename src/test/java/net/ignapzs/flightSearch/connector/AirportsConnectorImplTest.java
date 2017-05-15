package net.ignapzs.flightSearch.connector;

import net.ignapzs.flightSearch.connector.impl.AirportsConnectorImpl;
import net.ignapzs.flightSearch.model.Airport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AirportsConnectorImplTest {

    private Airport airportToTest;
    private AirportsConnectorImpl airportsConnectorToTest;

    @Before
    public void initMembers() {
        this.airportToTest = new Airport("code", "Madrid");
        this.airportsConnectorToTest = new AirportsConnectorImpl();
    }

    @After
    public void deleteMembers() {
        this.airportToTest = null;
        this.airportsConnectorToTest = null;
    }

    @Test
    public void addEntityTest() {
        airportsConnectorToTest.addEntity(airportToTest);

        assertTrue(airportsConnectorToTest.filterEntities(p -> true).count() == 1);
    }

    @Test
    public void removeEntityTest() {
        airportsConnectorToTest.addEntity(airportToTest);
        airportsConnectorToTest.removeEntities(p -> true);

        assertTrue(airportsConnectorToTest.filterEntities(p -> true).count() == 0);
    }

    @Test
    public void findEntityTest() {
        final int numberOfEntities = 3;
        for (int i = 0; i < numberOfEntities; i++) {
            airportsConnectorToTest.addEntity(airportToTest);
        }
        assertTrue(airportsConnectorToTest.filterEntities(p -> true).count() == numberOfEntities);
    }
}