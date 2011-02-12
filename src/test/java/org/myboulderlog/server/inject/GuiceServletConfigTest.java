package org.myboulderlog.server.inject;

import com.google.inject.Injector;
import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

import java.util.Map;

@Test(groups = TestUtils.UNIT)
public class GuiceServletConfigTest {

    @Test
    public void testGuiceConfigDoesNotThrowException() {
        GuiceServletConfig guiceServletConfig = new GuiceServletConfig();
        Injector injector = guiceServletConfig.getInjector();
        Map bindings = injector.getBindings();
    }
}
