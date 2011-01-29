package org.myboulderlog.server.inject;

import com.google.gwt.requestfactory.server.DefaultExceptionHandler;
import com.google.gwt.requestfactory.server.RequestFactoryServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * An implementation of the RequestFactoryServlet that is using Guice to inject the {@link InjectingServiceLayerDecorator}, so
 * it lets the {@link InjectingServiceLayerDecorator} to control the request processing.
 * <p/>
 * This servlet is bound in the {@link RequestFactoryInjectingModule} and could be injected
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 * @see RequestFactoryInjectingModule
 */
@Singleton
class InjectingRequestFactoryServlet extends RequestFactoryServlet {

    private static final long serialVersionUID = -8407850949583771729L;

    /**
     * Plugs the injectable decorator @{link InjectingServiceLayerDecorator} and the {@link DefaultExceptionHandler}, so the dependencies could be injected by the DI framework.
     */
    @Inject
    public InjectingRequestFactoryServlet(InjectingServiceLayerDecorator serviceLayerDecorator) {
        super(new DefaultExceptionHandler(), serviceLayerDecorator);
    }


}
