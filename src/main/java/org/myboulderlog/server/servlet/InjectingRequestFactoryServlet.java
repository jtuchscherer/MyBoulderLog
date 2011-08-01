package org.myboulderlog.server.servlet;

import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.myboulderlog.server.inject.InjectingServiceLayerDecorator;

/**
 * An implementation of the RequestFactoryServlet that is using Guice to inject the {@link org.myboulderlog.server.inject.InjectingServiceLayerDecorator}, so
 * it lets the {@link org.myboulderlog.server.inject.InjectingServiceLayerDecorator} to control the request processing.
 * <p/>
 * This servlet is bound in the {@link org.myboulderlog.server.inject.MyBoulderLogModule} and could be injected
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 * @see org.myboulderlog.server.inject.MyBoulderLogModule
 */
@Singleton
public class InjectingRequestFactoryServlet extends RequestFactoryServlet {

    private static final long serialVersionUID = -8407850949583771729L;

    /**
     * Plugs the injectable decorator @{link InjectingServiceLayerDecorator} and the {@link DefaultExceptionHandler}, so the dependencies could be injected by the DI framework.
     */
    @Inject
    public InjectingRequestFactoryServlet(InjectingServiceLayerDecorator serviceLayerDecorator) {
        super(new DefaultExceptionHandler(), serviceLayerDecorator);
    }


}
