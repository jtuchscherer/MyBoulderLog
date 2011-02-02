package org.myboulderlog;

import org.myboulderlog.server.model.Route;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestUtils {
    public static final String INTEGRATION = "Integration";
    public static final String UNIT = "Unit";

    public static void assertContains(List<? extends Object> list, Object... objects) {
        for(Object o : objects) {
            assertTrue(list.contains(o));
        }
    }
}
