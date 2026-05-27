package com.usapresidents.data.features.search;

import com.usapresidents.data.core.domain.models.President;
import org.springframework.data.jpa.domain.Specification;

public class PresidentSpecifications {

    public static Specification<President> hasParty(String party){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("party"), party);
    }

    public static Specification<President> bornInState(String stateBorn){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("stateBorn"), stateBorn);
    }

    public static Specification<President> birthYearGreatherThanOrEqual(Integer birthYearMin){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("birthYear"), birthYearMin);
    }

    public static Specification<President> birthYearLessThanOrEqual(Integer birthYearMax){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("birthYear"), birthYearMax);
    }
}
