package com.antologic.project.specification;

import com.antologic.project.entity.ProjectView;
import com.antologic.project.entity.ProjectView_;
import com.antologic.project.entity.UserView_;
import com.antologic.project.forms.FilterProjectForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProjectViewSpecification implements Specification<ProjectView> {

    private final FilterProjectForm filter;

    public ProjectViewSpecification(FilterProjectForm filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<ProjectView> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get(ProjectView_.name), filter.getName()));
        }

        if (filter.getBeginning() != null && filter.getFinishing() != null) {
            predicates.add(
                    criteriaBuilder.and(
                            criteriaBuilder.between(root.get(ProjectView_.beginning), filter.getBeginning(),
                                    filter.getFinishing()),
                            criteriaBuilder.between(root.get(ProjectView_.finishing), filter.getBeginning(), filter.getFinishing()
                            )));
        }

        if (filter.getUsers() != null) {
            filter.getUsers().forEach(login -> predicates.add(criteriaBuilder.equal(root.join(ProjectView_.usersView).get(UserView_.login), login)));
        }

        if (filter.getBudgetExpenses() != null) {
            predicates.add(criteriaBuilder.equal(root.get(ProjectView_.isExceeded), filter.getBudgetExpenses()));
        }
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
