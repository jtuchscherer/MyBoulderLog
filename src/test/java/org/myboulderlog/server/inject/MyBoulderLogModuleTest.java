package org.myboulderlog.server.inject;

import org.myboulderlog.TestUtils;
import org.testng.annotations.Test;

@Test(groups = TestUtils.UNIT)
public class MyBoulderLogModuleTest {

    @Test
    public void testBoulderLogModuleConfiguratioDoesNotThrowExceptionn() {

        MyBoulderLogModule myBoulderLogModule = new MyBoulderLogModule();
        myBoulderLogModule.configure();

    }
}
