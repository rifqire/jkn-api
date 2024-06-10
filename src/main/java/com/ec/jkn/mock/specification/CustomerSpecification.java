package com.ec.jkn.mock.specification;

import com.ec.jkn.mock.dto.request.SearchCustomerRequest;
import com.ec.jkn.mock.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }
            if (request.getBirthDate() != null) {
                Predicate birthDatePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("birthDate")), "%" + request.getBirthDate().toLowerCase() + "%");
                predicates.add(birthDatePredicate);
            }
            if (request.getBpjsNumber() != null) {
                Predicate bpjsPredicate = criteriaBuilder.equal(root.get("bpjsNumber"), request.getBpjsNumber());
                predicates.add(bpjsPredicate);
            }
            if (request.getFaskes() != null) {
                Predicate faskesPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("faskes")), "%" + request.getFaskes().toLowerCase() + "%");
                predicates.add(faskesPredicate);
            }
            if (request.getPhoneNumber() != null) {
                Predicate phonePredicate = criteriaBuilder.equal(root.get("phoneNumber"), request.getPhoneNumber());
                predicates.add(phonePredicate);
            }
            if (request.getIsActive() != null) {
                Predicate isActivePredicate = criteriaBuilder.equal(root.get("isActive"), request.getIsActive());
                predicates.add(isActivePredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
