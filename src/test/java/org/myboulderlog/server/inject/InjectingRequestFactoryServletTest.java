package org.myboulderlog.server.inject;

import org.myboulderlog.TestUtils;
import org.myboulderlog.server.servlet.InjectingRequestFactoryServlet;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

@Test(groups = TestUtils.UNIT)
public class InjectingRequestFactoryServletTest {

    @Test
    public void testInjectingRequestFactoryServletConstructorDoesNotThrowException() throws Exception {
        InjectingServiceLayerDecorator injectingServiceLayerDecorator = mock(InjectingServiceLayerDecorator.class);
        InjectingRequestFactoryServlet injectingRequestFactoryServlet = new InjectingRequestFactoryServlet(injectingServiceLayerDecorator);
    }
}
