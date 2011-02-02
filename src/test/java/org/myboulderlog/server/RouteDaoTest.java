package org.myboulderlog.server;


import com.googlecode.objectify.ObjectifyService;
import org.junit.Before;
import org.junit.Test;
import org.myboulderlog.server.dao.objectify.RouteDAOImpl;
import org.myboulderlog.server.model.Route;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link org.myboulderlog.server.dao.objectify.RouteDAOImpl} class.
 *
 * @author androns
 */
public class RouteDaoTest extends LocalDatastoreTest {

    private RouteDAOImpl routeDAOImpl;

    /**
     * @see LocalDatastoreTest#setUp()
     */
    @Before
    @Override
    public void setUp() {
        super.setUp();
        this.routeDAOImpl = new RouteDAOImpl();
        ObjectifyService.register(Route.class);
    }

    /**
     *
     */
    @Test
    public void smokeTest() {
        assertNotNull(this.routeDAOImpl);

        // create
        final Route route = new Route();
        route.setName("Test route");

        this.routeDAOImpl.saveAndReturn(route);

        // read
        Collection<Route> routes = this.routeDAOImpl.listAll();

        assertNotNull(routes);
        assertEquals(1, routes.size());
        final Route storedRoute = routes.iterator().next();

        assertNotNull(storedRoute.getId());
        assertEquals(route.getName(), storedRoute.getName());

        // delete
        this.routeDAOImpl.deleteById(storedRoute.getId());

        routes = this.routeDAOImpl.listAll();
        assertEquals(0, routes.size());
    }
}
