package org.myboulderlog.server.inject;

import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

@Test(groups = TestUtils.UNIT)
public class RequestFactoryInjectingModuleTest {

    @Test
    public void testRequestFactoryInjectingModuleConfiguratioDoesNotThrowExceptionn() {
        RequestFactoryInjectingModule requestFactoryInjectingModule = new RequestFactoryInjectingModule("testUrl");
        requestFactoryInjectingModule.configureServlets();
    }
}
