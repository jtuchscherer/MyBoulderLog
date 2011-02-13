package org.myboulderlog.server.inject;

import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.TestUtils;
import org.myboulderlog.server.model.AbstractDomainObject;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

@Test(groups = TestUtils.INTEGRATION)
public class GuiceServletConfigTest {

    private GuiceServletConfig guiceServletConfig;

    @BeforeMethod
    public void setUp() {
        guiceServletConfig = new GuiceServletConfig();
    }

    @Test
    public void testContextInitialized() throws Exception {

        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        ServletContext servletContext = mock(ServletContext.class);
        when(servletContextEvent.getServletContext()).thenReturn(servletContext);
        Injector injector = mock(Injector.class);
        when(servletContext.getAttribute(GuiceServletConfig.INJECTOR_NAME)).thenReturn(injector);
        ObjectifyFactory factory = mock(ObjectifyFactory.class);
        when(injector.getInstance(ObjectifyFactory.class)).thenReturn(factory);
        guiceServletConfig.contextInitialized(servletContextEvent);
        Reflections reflections = new Reflections(new ConfigurationBuilder()
          .setUrls(ClasspathHelper.getUrlsForPackagePrefix("org.myboulderlog.server"))
          .setScanners(new SubTypesScanner()));

        Set<Class<? extends AbstractDomainObject>> subTypes = reflections.getSubTypesOf(AbstractDomainObject.class);
        for(Class<? extends AbstractDomainObject> subType : subTypes) {
            ObjectifyService.factory().getMetadata(subType);
        }
    }

    @Test
    public void testGuiceConfigDoesNotThrowException() {
        Injector injector = guiceServletConfig.getInjector();
        Map bindings = injector.getBindings();
    }
}
