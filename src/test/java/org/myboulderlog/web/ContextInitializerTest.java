package org.myboulderlog.web;

import com.googlecode.objectify.ObjectifyFactory;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.model.AbstractDomainObject;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContextEvent;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

@Test(groups = TestUtils.UNIT)
public class ContextInitializerTest {

    private ContextInitializer contextInitializer;
    private ObjectifyFactory factory;

    @BeforeMethod
    public void setUp() {
        factory = mock(ObjectifyFactory.class);
        contextInitializer = new ContextInitializer(factory);
    }

    @Test
    public void testContextInitialized() throws Exception {

        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        contextInitializer.contextInitialized(servletContextEvent);
        Reflections reflections = new Reflections(new ConfigurationBuilder()
          .filterInputsBy(new FilterBuilder.Include(FilterBuilder.prefix("org.myboulderlog.server")))
          .setUrls(ClasspathHelper.getUrlsForPackagePrefix("org.myboulderlog.server"))
          .setScanners(new SubTypesScanner()));

        Set<Class<? extends AbstractDomainObject>> subTypes = reflections.getSubTypesOf(AbstractDomainObject.class);
        for(Class<? extends AbstractDomainObject> subType : subTypes) {
            verify(factory).register(subType);
        }
    }

    @Test
    public void testContextDestroyed() throws Exception {
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        contextInitializer.contextDestroyed(servletContextEvent);
    }
}
