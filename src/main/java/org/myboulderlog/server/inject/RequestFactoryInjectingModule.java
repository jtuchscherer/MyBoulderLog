package org.myboulderlog.server.inject;

import com.google.inject.servlet.ServletModule;

/**
 * RequestFactoryInjectingModule contains configuration of the RequestFactory injection mechanism.
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public final class RequestFactoryInjectingModule extends ServletModule {

    private final String targetUrl;

    public RequestFactoryInjectingModule(final String targetUrl) {
        this.targetUrl = targetUrl;
    }


    @Override
    protected void configureServlets() {
        serve(targetUrl).with(InjectingRequestFactoryServlet.class);
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
