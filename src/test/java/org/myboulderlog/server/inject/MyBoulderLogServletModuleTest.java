package org.myboulderlog.server.inject;

import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

@Test(groups = TestUtils.UNIT)
public class MyBoulderLogServletModuleTest {

    @Test
    public void testBoulderLogServletModuleConfiguratioDoesNotThrowExceptionn() {

        MyBoulderLogServletModule myBoulderLogServletModule = new MyBoulderLogServletModule();
        myBoulderLogServletModule.configureServlets();

    }
}
