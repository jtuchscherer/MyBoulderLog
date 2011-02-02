package org.myboulderlog.server;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Performs datastore setup, as described <a
 * href="http://code.google.com/appengine/docs/java/howto/unittesting.html">here</a>.
 *
 * @author androns
 */
public abstract class LocalDatastoreTest {

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


    /**
     *
     */
    @BeforeClass
    public void setUp() {
        this.helper.setUp();
    }

    /**
     * @see LocalServiceTest#tearDown()
     */
    @AfterClass
    public void tearDown() {
        this.helper.tearDown();
    }
}
