package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.LocalDatastoreTest;
import org.myboulderlog.server.model.Route;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.myboulderlog.TestUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = INTEGRATION)
public class AbstractObjectifyDAOTest extends LocalDatastoreTest {

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
    public void testGetByProperty() throws Exception {
        String name = "testGetByProperty";

        Route route = new Route();
        route.setName(name);
        Key<Route> originialKey = this.routeDAOImpl.put(route);

        Route returnedRoute = this.routeDAOImpl.getByProperty("name", name);

        assertEquals(originialKey, this.routeDAOImpl.getKey(returnedRoute.getId()));

    }

    @Test
    public void testListByProperty() throws Exception {
        long rating = 1L;

        Route route = new Route();
        route.setRating(rating);
        Key<Route> originialKey = this.routeDAOImpl.put(route);

        Route anotherRoute = new Route();
        anotherRoute.setRating(rating);
        Key<Route> anotherKey = this.routeDAOImpl.put(anotherRoute);

        List<Route> returnedRoutes = this.routeDAOImpl.listByProperty("rating", rating);

        for(Route r :returnedRoutes) {
            assertTrue(r.getId().equals(route.getId()) || r.getId().equals(anotherRoute.getId()));
        }

    }



    @Test
    public void testListKeysByProperty() throws Exception {

    }

    @Test
    public void testGetByExample() throws Exception {

    }

    @Test
    public void testListByExample() throws Exception {

    }

    @Test
    public void testListChildren() throws Exception {

    }

    @Test
    public void testListChildKeys() throws Exception {

    }
}
