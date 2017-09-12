package com.tdt.validation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object>, ApplicationContextAware {

    private Class clazz;
    private String name;
    private ApplicationContext ctx;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void initialize(Unique constraintAnnotation) {
        name = constraintAnnotation.name();
        clazz = constraintAnnotation.clazz();
        entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
            Root root = criteriaQuery.from(clazz);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(name), value));
            TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message, String field) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}