package org.myboulderlog.server.locator;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.model.AbstractDomainObject;
import org.myboulderlog.server.model.Route;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test(groups = TestUtils.UNIT)
public class ObjectifyLocatorTest {

    private ObjectifyLocator objectifyLocator;
    private DAOBase daoBase;

    @BeforeMethod
    public void setUp() {
        daoBase = mock(DAOBase.class);
        objectifyLocator = new ObjectifyLocator(daoBase);
    }

    @Test
    public void testCreate() throws Exception {
        AbstractDomainObject route = objectifyLocator.create(Route.class);
        assertTrue(route instanceof Route);
    }

    @Test
    public void testFind() throws Exception {
        Objectify objectify = mock(Objectify.class);
        when(daoBase.ofy()).thenReturn(objectify);
        objectifyLocator.find(Route.class, 1L);
        verify(objectify).find(Route.class, 1L);
    }

    @Test
    public void testGetDomainType() throws Exception {
        assertNull(objectifyLocator.getDomainType());
    }

    @Test
    public void testGetId() throws Exception {
        Route route = new Route();
        Long id = 1L;
        route.setId(id);
        Long returnedId = objectifyLocator.getId(route);
        assertEquals(id,returnedId);
    }

    @Test
    public void testGetIdType() throws Exception {
        Class clazz = objectifyLocator.getIdType();
        assertEquals(clazz,Long.class);
    }

    @Test
    public void testGetVersion() throws Exception {
        Route route = new Route();
        Integer version = 1;
        route.setVersion(version);
        Integer returnedVersion = (Integer) objectifyLocator.getVersion(route);
        assertEquals(version,returnedVersion);
    }
}
