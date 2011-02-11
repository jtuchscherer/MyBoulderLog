package org.myboulderlog.server.model;

import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = TestUtils.UNIT)
public class RouteTest {

    @Test
    public void testSetName() throws Exception {
        Route route = new Route();
        String testName = "testName";
        route.setName(testName);
        assertEquals(route.getName(),testName);
    }

    @Test
    public void testSetRating() throws Exception {
        Route route = new Route();
        Long testRating = 1L;
        route.setRating(testRating);
        assertEquals(route.getRating(),testRating);
    }

    @Test
    public void testSetDescription() throws Exception {
        Route route = new Route();
        String testDescription = "testDescription";
        route.setDescription(testDescription);
        assertEquals(route.getDescription(), testDescription);
    }

    @Test
    public void testSetArea() throws Exception {
        Route route = new Route();
        Long testArea = 1L;
        route.setArea(testArea);
        assertEquals(route.getArea(),testArea);
    }

    @Test
    public void testSetId() throws Exception {
        Route route = new Route();
        Long testId = 1L;
        route.setId(testId);
        assertEquals(route.getId(),testId);
    }

    @Test
    public void testSetVersion() throws Exception {
        Route route = new Route();
        Integer testVersion = 1;
        route.setVersion(testVersion);
        assertEquals(route.getVersion(),testVersion);
    }
}
