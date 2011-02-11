package org.myboulderlog.server.locator;

import com.google.inject.Injector;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.model.Route;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Test(groups = TestUtils.UNIT)
public class DaoServiceLocatorTest {

    private DaoServiceLocator daoServiceLocator;
    private Injector injector;

    @BeforeMethod
    public void setUp() {
        injector = mock(Injector.class);
        daoServiceLocator = new DaoServiceLocator(injector);
    }

    @Test
    public void testGetInstance() throws Exception {
        Route route = new Route();
        when(injector.getInstance(Route.class)).thenReturn(route);
        Object returnedRoute = daoServiceLocator.getInstance(Route.class);
        assertEquals(route,returnedRoute);
    }
}
