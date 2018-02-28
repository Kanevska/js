package util;

import exception.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import org.apache.log4j.Logger;
import service.serviceImpl.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static metadata.LoggerConst.IS_INVALID;


public class ValidationUtil {

    private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);

    public void validate(Object bean) throws ValidationException {

        Map<String, List<String>> errorMap = new HashMap<>();
        Validator validator = new ValidatorConfigurer().getValidator();

        List<ConstraintViolation> violations = validator.validate(bean);
        if ((violations.size() > 0)) {
            String error = String.format(IS_INVALID, bean.getClass().getName());
            LOGGER.error(error);

            for (ConstraintViolation violation : violations) {
                FieldContext fieldContext = (FieldContext) violation.getContext();
                String key = fieldContext.getField().getName();
                String value = violation.getMessage();
                if (errorMap.containsKey(key)) {
                    errorMap.get(key).add(value);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(value);
                    errorMap.put(key, list);
                }
            }
            throw new ValidationException(errorMap);
        }

    }


}
