package com.tdt.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Matches annotation validator
 */
public class MatchesValidator implements ConstraintValidator<Matches, Object> {

    private String[] fields;
    private String[] verifyFields;

    public void initialize(Matches constraintAnnotation) {
        fields = constraintAnnotation.fields();
        verifyFields = constraintAnnotation.verifyFields();
    }


    public boolean isValid(Object value, ConstraintValidatorContext context) {

        boolean matches = true;

        for (int i = 0; i < fields.length; i++) {
            Object fieldObj, verifyFieldObj;
            // try to get values from bean by field name
            try {
                fieldObj = BeanUtils.getProperty(value, fields[i]);
                verifyFieldObj = BeanUtils.getProperty(value, verifyFields[i]);
            } catch (Exception e) {
                //ignore
                continue;
            }
            //ignore fields that do not marked by Matches annotation
            boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);
            if (neitherSet) {
                continue;
            }
            // check the matching
            boolean tempMatches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

            // return message when validation failed
            if (!tempMatches) {
                addConstraintViolation(context, fields[i] + " fields do not match", verifyFields[i]);
            }

            matches = matches ? tempMatches : matches;
        }
        return matches;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message, String field) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();
    }
}