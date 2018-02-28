package util;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;

import static net.sf.oval.integration.spring.SpringCheckInitializationListener.INSTANCE;


public class ValidatorConfigurer {

    private Validator validator;

    public Validator getValidator() {
        return validator;
    }

    public ValidatorConfigurer() {
        final AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(INSTANCE);
        this.validator = new Validator(myConfigurer);
    }
}
