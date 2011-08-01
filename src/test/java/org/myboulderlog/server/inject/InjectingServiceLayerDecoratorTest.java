package org.myboulderlog.server.inject;

import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.locator.ObjectifyLocator;
import org.myboulderlog.server.model.Route;
import org.myboulderlog.shared.request.RouteRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = TestUtils.INTEGRATION)
public class InjectingServiceLayerDecoratorTest {

    private Injector injector;

    @BeforeClass
    public void setUp() {
        GuiceServletConfig guiceServletConfig = new GuiceServletConfig();
        injector = guiceServletConfig.getInjector();
    }

    @Test
    public void testCreateLocator() throws Exception {
        Route route = new Route();
        Long id = 1L;
        route.setId(id);
        InjectingServiceLayerDecorator injectingServiceLayerDecorator = new InjectingServiceLayerDecorator(injector);
        ObjectifyLocator locator = injectingServiceLayerDecorator.createLocator(ObjectifyLocator.class);
        Long returnedId = locator.getId(route);
        assertEquals(id, returnedId);
    }

    @Test
    public void testCreateServiceInstance() throws Exception {
        Class routeRequestClazz = RouteRequest.class;
        Method listAllMethod = routeRequestClazz.getMethod("listAll");
        InjectingServiceLayerDecorator injectingServiceLayerDecorator = new InjectingServiceLayerDecorator(injector);
        Object serviceInstance = injectingServiceLayerDecorator.createServiceInstance((Class<? extends RequestContext>) listAllMethod.getDeclaringClass());
        assertTrue(serviceInstance instanceof RouteDAO);
    }
}
