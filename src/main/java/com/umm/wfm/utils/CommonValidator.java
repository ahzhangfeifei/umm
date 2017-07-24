package com.umm.wfm.utils;

import com.umm.wfm.intf.constants.BizzEx;
import com.umm.wfm.intf.exception.BizzException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wangshiqi on 2017/7/10.
 */
@Component
public class CommonValidator {

    @Resource
    private Validator validator;

    private List<String> validate(Object obj, String... fields) {
        List<String> violationList = new ArrayList<String>();
        Set<ConstraintViolation<Object>> constraintViolations = null;
        if (fields.length == 0) {
            constraintViolations = validator.validate(obj);
        } else {
            constraintViolations = new HashSet<ConstraintViolation<Object>>();
            for (String field : fields) {
                constraintViolations.addAll(validator.validateProperty(obj, field));
            }
        }

        for (ConstraintViolation<Object> violation : constraintViolations) {
            StringBuilder sb = new StringBuilder();
            sb.append(violation.getPropertyPath()).append(violation.getMessage());
            violationList.add(sb.toString());
        }
        return violationList;
    }

    public void validateInput(Object obj, String... fields) {
        if (obj == null) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "入参不能为空");
        }
        List<String> violationList = validate(obj, fields);
        if (violationList.isEmpty() == false) {
            StringBuilder sb = new StringBuilder();
            for (String msg : violationList) {
                sb.append(msg).append(";");
            }
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "入参异常：" + sb.toString());
        }
    }

    public void validateInputExclude(Object obj, String... excludeFields) {
        if (obj == null || excludeFields.length == 0) {
            validateInput(obj);
        }

        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        for(Iterator it = constraintViolations.iterator(); it.hasNext(); ){
            boolean excludeFlag = false;
            ConstraintViolation<Object> violation = (ConstraintViolation<Object>) it.next();
            for (String excludeField : excludeFields) {
                if(violation.getPropertyPath().toString().equals(excludeField)){
                    excludeFlag = true;
                    it.remove();
                    break;
                }
            }
            if(!excludeFlag){
                sb.append(violation.getPropertyPath()).append(violation.getMessage()).append(";");
            }
        }
        if(CollectionUtils.isNotEmpty(constraintViolations)){
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "入参异常：" + sb.toString());
        }
    }


}
