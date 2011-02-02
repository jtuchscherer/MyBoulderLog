package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.LocalDatastoreTest;
import org.myboulderlog.server.model.Route;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.myboulderlog.TestUtils.INTEGRATION;
import static org.testng.Assert.assertEquals;

@Test(groups= INTEGRATION)
public class RouteDAOImplIntegrationTest extends LocalDatastoreTest {

    private RouteDAOImpl routeDAOImpl;

    /**
     * @see org.myboulderlog.server.LocalDatastoreTest#setUp()
     */
    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        this.routeDAOImpl = new RouteDAOImpl();
        ObjectifyService.register(Route.class);
    }

    @Test
    public void testSaveAndReturn() throws Exception {
        // create
        final Route route = new Route();
        route.setName("Test route");

        Route savedRoute = this.routeDAOImpl.saveAndReturn(route);

        assertEquals(route.getName(), savedRoute.getName());

    }

    @Test
    public void testDeleteById() throws Exception {
        //create
        final Route route = new Route();
        route.setName("Test route");

        Route storedRoute = this.routeDAOImpl.saveAndReturn(route);

        // delete
        this.routeDAOImpl.deleteById(storedRoute.getId());

        List<Route> routes = this.routeDAOImpl.listAll();
        assertEquals(0, routes.size());
    }
}
