package org.myboulderlog.server.inject;

import com.google.inject.servlet.ServletModule;
import org.myboulderlog.server.servlet.InjectingRequestFactoryServlet;
import org.myboulderlog.server.servlet.RemoteLoggingServlet;

public class MyBoulderLogServletModule extends ServletModule {

    private final String targetUrl;

    public MyBoulderLogServletModule(final String targetUrl) {
        this.targetUrl = targetUrl;
    }


    @Override
    protected void configureServlets() {
        serve(targetUrl).with(InjectingRequestFactoryServlet.class);
        serve("/admin/remote_logging").with(RemoteLoggingServlet.class);
        serve("/myboulderlog/remote_logging").with(RemoteLoggingServlet.class);
    }

//  /**
//   * Creates and reuses injecting JSR 303 Validator factory.
//   */
//  @Provides
//  @Singleton
//  public ValidatorFactory getValidatorFactory(Injector injector) {
//    return Validation.byDefaultProvider().configure().constraintValidatorFactory(new InjectingConstraintValidationFactory(injector)).buildValidatorFactory();
//  }
//
//  /**
//   * Creates and reuses injecting JSR 303 Validator.
//   */
//  @Provides
//  @Singleton
//  public Validator getValidator(ValidatorFactory validatorFactory) {
//    return validatorFactory.getValidator();
//  }
}
