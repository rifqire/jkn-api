package com.ec.jkn.mock.specification;

import com.ec.jkn.mock.dto.request.SearchDoctorRequest;
import com.ec.jkn.mock.entity.Doctor;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DoctorSpecification {
    public static Specification<Doctor> getSpecification(SearchDoctorRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }
            if (request.getStrNumber() != null) {
                Predicate strPredicate = criteriaBuilder.equal(root.get("strNumber"), request.getStrNumber());
                predicates.add(strPredicate);
            }
            if (request.getSpecialization() != null) {
                Predicate specializationPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("specialization")), "%" + request.getSpecialization().toLowerCase() + "%");
                predicates.add(specializationPredicate);
            }
            if (request.getExperienceYears() != null) {
                Predicate expPredicate = criteriaBuilder.equal(root.get("experienceYears"), request.getExperienceYears());
                predicates.add(expPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
