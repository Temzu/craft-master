package com.gb.agile.craft_master.repositories.specifications;

import com.gb.agile.craft_master.model.entities.Offer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public class OfferSpecifications {

    private static Specification<Offer> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",title));
    }
    public static Specification<Offer> build(MultiValueMap<String, String> params) {
        Specification<Offer> spec = Specification.where(null);
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
