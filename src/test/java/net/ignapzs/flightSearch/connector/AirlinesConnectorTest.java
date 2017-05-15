package net.ignapzs.flightSearch.connector;

import net.ignapzs.flightSearch.connector.impl.AirlinesConnectorImpl;
import net.ignapzs.flightSearch.model.Airline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AirlinesConnectorTest {

    private Airline airlineToTest;
    private AirlinesConnectorImpl airlineConnectorToTest;

    @Before
    public void initMembers() {
        this.airlineToTest = new Airline("CODE", "NAME", Optional.of(10.0));
        this.airlineConnectorToTest = new AirlinesConnectorImpl();
    }

    @After
    public void deleteMembers() {
        this.airlineToTest = null;
        this.airlineConnectorToTest = null;
    }

    @Test
    public void addEntityTest() {
        airlineConnectorToTest.addEntity(airlineToTest);

        assertTrue(airlineConnectorToTest.filterEntities(p -> true).count() == 1);
    }

    @Test
    public void removeEntityTest() {
        airlineConnectorToTest.addEntity(airlineToTest);
        airlineConnectorToTest.removeEntities(p -> true);

        assertTrue(airlineConnectorToTest.filterEntities(p -> true).count() == 0);
    }

    @Test
    public void findEntityTest() {
        final int numberOfEntities = 3;
        for (int i = 0; i < numberOfEntities; i++) {
            airlineConnectorToTest.addEntity(airlineToTest);
        }
        assertTrue(airlineConnectorToTest.filterEntities(p -> true).count() == numberOfEntities);
    }

}