package net.ignapzs.flightSearch.connector;

import net.ignapzs.flightSearch.connector.impl.PricingRulesConnectorImpl;
import net.ignapzs.flightSearch.model.PricingRules;
import net.ignapzs.flightSearch.predicates.PricingRulesPredicates;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PricingRulesConnectorImplTest {

    private PricingRules pricingRulesToTest;
    private PricingRulesConnectorImpl pricingRulesConnectorToTest;

    @Before
    public void initMembers() {
        this.pricingRulesToTest = new PricingRules(0, 15, 100);
        this.pricingRulesConnectorToTest = new PricingRulesConnectorImpl();
    }

    @After
    public void deleteMembers() {
        this.pricingRulesToTest = null;
        this.pricingRulesConnectorToTest = null;
    }


    @Test
    public void addEntityTest() {
        pricingRulesConnectorToTest.addEntity(pricingRulesToTest);

        assertTrue(pricingRulesConnectorToTest.filterEntities(PricingRulesPredicates.isBetween(5)).count() == 1);
    }


    @Test
    public void findEntityTest() {
        final int numberOfEntities = 3;
        for (int i = 0; i < numberOfEntities; i++) {
            pricingRulesConnectorToTest.addEntity(pricingRulesToTest);
        }

        assertTrue(pricingRulesConnectorToTest.filterEntities(PricingRulesPredicates.isBetween(5)).count() == numberOfEntities );
    }

}