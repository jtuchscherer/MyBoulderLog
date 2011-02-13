package org.myboulderlog.server.inject;

import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

@Test(groups = TestUtils.UNIT)
public class RequestFactoryInjectingModuleTest {

    @Test
    public void testRequestFactoryInjectingModuleConfigurationDoesNotThrowException() {
        RequestFactoryInjectingModule requestFactoryInjectingModule = new RequestFactoryInjectingModule("testUrl");
        requestFactoryInjectingModule.configureServlets();
    }
}
