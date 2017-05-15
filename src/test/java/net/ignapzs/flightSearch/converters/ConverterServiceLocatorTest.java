package net.ignapzs.flightSearch.converters;

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
@PrepareForTest({ConverterServiceLocator.class})
public class ConverterServiceLocatorTest {

    private final static String FIELD = "INSTANCE";

    @Test
    public void ConverterServiceLocatorTest() {
        FlightToFlightSearchResponseConverter flightToFlightSearchResponseConverter = mock(FlightToFlightSearchResponseConverter.class);
        ConverterServiceLocator converterServiceLocator = mock(ConverterServiceLocator.class);
        Whitebox.setInternalState(ConverterServiceLocator.class, FIELD, converterServiceLocator);

        when(converterServiceLocator.getFlightToFlightSearchResponseConverter()).thenReturn(flightToFlightSearchResponseConverter);

        ConverterServiceLocator.INSTANCE.getFlightToFlightSearchResponseConverter();

        verify(converterServiceLocator, times(1)).getFlightToFlightSearchResponseConverter();
    }
}