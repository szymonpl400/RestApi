package com.antologic.project.specification;

import com.antologic.project.entity.User_;
import com.antologic.project.forms.FilterUserForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<FilterUserForm> {

    private FilterUserForm filter;

    public UserSpecification(FilterUserForm filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<FilterUserForm> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getLogin() != null) {
            predicates.add(criteriaBuilder.like(root.get(User_.LOGIN), filter.getLogin()));
        }
        if (filter.getFirstName() != null) {
            predicates.add(criteriaBuilder.like(root.get(User_.FIRST_NAME), filter.getFirstName()));
        }
        if (filter.getLastName() != null) {
            predicates.add(criteriaBuilder.like(root.get(User_.LAST_NAME), filter.getLastName()));
        }
        if (filter.getAccountType() != null) {
            predicates.add(criteriaBuilder.equal(root.get(User_.ACCOUNT_TYPE), filter.getAccountType()));
        }
        if (filter.getCostFrom().intValue() != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(User_.COST_PER_HOUR), filter.getCostFrom()));
        }
        if (filter.getCostTo().intValue() != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(User_.COST_PER_HOUR), filter.getCostFrom()));
        }
        if (predicates.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get(User_.FIRST_NAME), -1));
        }

        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
