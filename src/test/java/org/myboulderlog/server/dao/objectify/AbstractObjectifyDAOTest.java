package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.server.LocalDatastoreTest;
import org.myboulderlog.server.model.Route;
import org.myboulderlog.shared.exception.TooManyResultsException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.myboulderlog.TestUtils.INTEGRATION;
import static org.myboulderlog.TestUtils.assertContains;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test(groups = INTEGRATION)
public class AbstractObjectifyDAOTest extends LocalDatastoreTest {

    private RouteDAOImpl routeDAOImpl;
    private final Random random = new Random();

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
    public void testKey() throws Exception {
        Route route = new Route();
        Key<Route> key = this.routeDAOImpl.put(route);

        this.routeDAOImpl.get(route.getId());

        Key<Route> result = this.routeDAOImpl.key(route);

        assertEquals(key,result);
    }

    @Test
    public void testGetByKeyCollection() throws Exception {
        Route route1 = new Route();
        Route route2 = new Route();
        Map<Key<Route>,Route> putMap = this.routeDAOImpl.putAll(Arrays.asList(route1,route2));
        List<Long> idList = Arrays.asList(route1.getId(),route2.getId());
        Map<Key<Route>,Route> getMap = this.routeDAOImpl.get(putMap.keySet());

        for(Key<Route> key : getMap.keySet()) {
            assertContains(idList, getMap.get(key).getId());
        }

    }

    @Test
    public void testDelete() throws Exception {
        Route route = new Route();
        this.routeDAOImpl.put(route);

        Route returnedRoute = this.routeDAOImpl.get(route.getId());

        assertEquals(returnedRoute.getId(), route.getId());

        this.routeDAOImpl.delete(route);

        try {
            this.routeDAOImpl.get(route.getId());
            fail();
        } catch (NotFoundException e) {

        }

    }

    @Test
    public void testDeleteKey() throws Exception {
        Route route = new Route();
        Key<Route> key = this.routeDAOImpl.put(route);

        Route returnedRoute = this.routeDAOImpl.get(route.getId());

        assertEquals(returnedRoute.getId(), route.getId());

        this.routeDAOImpl.deleteKey(key);

        try {
            this.routeDAOImpl.get(route.getId());
            fail();
        } catch (NotFoundException e) {

        }

    }

    @Test
    public void testDeleteAll() throws Exception {
        Route route1 = new Route();
        Route route2 = new Route();
        this.routeDAOImpl.putAll(Arrays.asList(route1,route2));

        Route returnedRoute1 = this.routeDAOImpl.get(route1.getId());
        assertEquals(returnedRoute1.getId(), route1.getId());
        Route returnedRoute2 = this.routeDAOImpl.get(route2.getId());
        assertEquals(returnedRoute2.getId(), route2.getId());

        this.routeDAOImpl.deleteAll(Arrays.asList(route1,route2));

        try {
            this.routeDAOImpl.get(route1.getId());
            fail();
        } catch (NotFoundException e) {

        }
        try {
            this.routeDAOImpl.get(route2.getId());
            fail();
        } catch (NotFoundException e) {

        }

    }

    @Test
    public void testDeleteKeys() throws Exception {
        Route route1 = new Route();
        Route route2 = new Route();
        Map<Key<Route>,Route> map = this.routeDAOImpl.putAll(Arrays.asList(route1,route2));

        Route returnedRoute1 = this.routeDAOImpl.get(route1.getId());
        assertEquals(returnedRoute1.getId(), route1.getId());
        Route returnedRoute2 = this.routeDAOImpl.get(route2.getId());
        assertEquals(returnedRoute2.getId(), route2.getId());

        this.routeDAOImpl.deleteKeys(map.keySet());

        try {
            this.routeDAOImpl.get(route1.getId());
            fail();
        } catch (NotFoundException e) {

        }
        try {
            this.routeDAOImpl.get(route2.getId());
            fail();
        } catch (NotFoundException e) {

        }

    }

    @Test
    public void testGetByProperty() throws Exception {
        String name = "testGetByProperty";

        Route route = new Route();
        route.setName(name);
        Key<Route> originalKey = this.routeDAOImpl.put(route);

        Route returnedRoute = this.routeDAOImpl.getByProperty("name", name);

        assertEquals(originalKey, this.routeDAOImpl.getKey(returnedRoute.getId()));

    }

    @Test
    public void testGetByPropertyShouldFailIfThereIsMoreThanOneMatch() throws Exception {
        String name = "testGetByPropertyShouldFailIfThereIsMoreThanOneMatch";

        Route route = new Route();
        route.setName(name);
        this.routeDAOImpl.put(route);

        Route route2 = new Route();
        route2.setName(name);
        this.routeDAOImpl.put(route2);

        try {
            this.routeDAOImpl.getByProperty("name", name);
            fail();
        } catch (TooManyResultsException e) {

        }

    }

    @Test
    public void testListByProperty() throws Exception {
        long rating = random.nextLong();

        Route route1 = new Route();
        route1.setRating(rating);
        this.routeDAOImpl.put(route1);

        Route route2 = new Route();
        route2.setRating(rating);
        this.routeDAOImpl.put(route2);

        List<Route> returnedRoutes = this.routeDAOImpl.listByProperty("rating", rating);

        for (Route r : returnedRoutes) {
            assertTrue(r.getId().equals(route1.getId()) || r.getId().equals(route2.getId()));
        }

    }

    @Test
    public void testListKeysByProperty() throws Exception {
        String descr = "descr";
        Route route = new Route();
        route.setDescription(descr);
        Key<Route> key1 = this.routeDAOImpl.put(route);

        Route route2 = new Route();
        route2.setDescription(descr);
        Key<Route> key2 = this.routeDAOImpl.put(route2);

        List<Key<Route>> list = this.routeDAOImpl.listKeysByProperty("description", descr);

        assertContains(list, key1, key2);

    }

    @Test
    public void testGetByExample() throws Exception {
        String descr = "testGetByExample";
        long rating = random.nextLong();
        long area = random.nextLong();

        Route route = new Route();
        route.setRating(rating);
        route.setDescription(descr);
        route.setArea(area);
        this.routeDAOImpl.put(route);

        Route example = new Route();
        example.setRating(rating);
        example.setDescription(descr);
        example.setArea(area);

        Route result = this.routeDAOImpl.getByExample(example);

        assertEquals(result.getId(), route.getId());

    }

    @Test
    public void testGetByExampleShouldFailIfThereIsMoreThanOneResult() throws Exception {
        String descr = "descr";
        long rating = random.nextLong();
        long area = random.nextLong();

        Route route = new Route();
        route.setRating(rating);
        route.setDescription(descr);
        route.setArea(area);
        this.routeDAOImpl.put(route);

        Route route2 = new Route();
        route2.setRating(rating);
        route2.setDescription(descr);
        route2.setArea(area);
        this.routeDAOImpl.put(route2);

        Route example = new Route();
        example.setRating(rating);
        example.setDescription(descr);
        example.setArea(area);

        try {
            this.routeDAOImpl.getByExample(example);
            fail();
        } catch (TooManyResultsException e) {

        }
    }

    @Test
    public void testListByExample() throws Exception {
        String descr = "descr";
        long rating = 1L;
        long area = 2L;

        Route route = new Route();
        route.setRating(rating);
        route.setDescription(descr);
        route.setArea(area);
        this.routeDAOImpl.put(route);

        Route route2 = new Route();
        route2.setRating(rating);
        route2.setDescription(descr);
        route2.setArea(area);
        this.routeDAOImpl.put(route2);

        Route example = new Route();
        example.setRating(rating);
        example.setDescription(descr);
        example.setArea(area);

        List<Route> result = this.routeDAOImpl.listByExample(example);

        for (Route r : result) {
            assertTrue(r.getId().equals(route.getId()) || r.getId().equals(route2.getId()));
        }
    }
}
