package net.ignapzs.flightSearch.service;

import net.ignapzs.flightSearch.service.pricing.PassengerTypeImpl;
import net.ignapzs.flightSearch.service.pricing.PricingRuleImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceLocator.class})
public class ServiceLocatorTest {

    private final static String FIELD_NAME = "INSTANCE";
    private PassengerTypeImpl passengerTypeToTest;
    private ServiceLocator serviceLocatorToTest;
    private PricingRuleImpl pricingRuleImplToTest;

    @Before

    public void initMembers() {
        this.passengerTypeToTest = mock(PassengerTypeImpl.class);
        this.serviceLocatorToTest = mock(ServiceLocator.class);
        this.pricingRuleImplToTest = mock(PricingRuleImpl.class);
        Whitebox.setInternalState(ServiceLocator.class, FIELD_NAME, serviceLocatorToTest);
    }


    @After
    public void deleteMembers() {
        this.passengerTypeToTest = null;
        this.serviceLocatorToTest = null;
    }


    @Test
    public void getPassengerTypeCalculator() {
        when(serviceLocatorToTest.getPassagerTypeCalculator()).thenReturn(passengerTypeToTest);

        ServiceLocator.INSTANCE.getPassagerTypeCalculator();

        verify(serviceLocatorToTest, times(1)).getPassagerTypeCalculator();
    }

    @Test
    public void getPricingRulesCalculator() {
        when(serviceLocatorToTest.getPricingRulesCalculator()).thenReturn(pricingRuleImplToTest);

        ServiceLocator.INSTANCE.getPricingRulesCalculator();

        verify(serviceLocatorToTest, times(1)).getPricingRulesCalculator();
    }
}