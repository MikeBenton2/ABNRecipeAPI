package com.abn.recipeapi_v1.filterAndSearch;

import com.abn.recipeapi_v1.model.Recipe;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification implements Specification<Recipe> {

    private SearchRequest criteria;

    public RecipeSpecification(SearchRequest criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        if (criteria.getOperation().equalsIgnoreCase("!=")) {
//            RecipeIngredient recipeIngredient = new RecipeIngredient();
//            recipeIngredient.setIngredient(new Ingredient(String.valueOf(criteria.getValue())));
//            return criteriaBuilder.isNotMember(recipeIngredient, root.get("recipeIngredients"));
//            return criteriaBuilder.notEqual(root.get("recipeIngredients").<Ingredient> get("ingredient").<String> get("name"), criteria.getValue());

//            CriteriaBuilder.In<Object> inClause = criteriaBuilder.in(root.get(criteria.getKey()));
//            inClause.value(criteria.getValue());
//            Predicate predicate = criteriaBuilder.in(inClause);
//            return predicate;
//        }
        Predicate predicate = null;

        for (Filter filter : criteria.filters()) {
            if (filter.getOperation().equalsIgnoreCase(":")) {
                if (root.get(filter.getKey()).getJavaType() == String.class) {
                    predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(
                            root.<String>get(filter.getKey())), "%" + String.valueOf(filter.getValue()).toLowerCase() + "%"));
                } else {
                    predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get(filter.getKey()), filter.getValue()));
                }
            }
        }

        return predicate;
    }

    public static Pageable getSortingOrder(SearchRequest searchRequest) {
        Pageable pageRequest = PageRequest.of(0,10);

        if(searchRequest.page() != null && searchRequest.numberOfElements() != null) {
            if(searchRequest.sortBy() != null) {
                if(searchRequest.orderBy() != null) {
                    switch (searchRequest.orderBy()) {
                        case ASCENDING -> pageRequest = PageRequest.of(searchRequest.page(), searchRequest.numberOfElements(), Sort.by(searchRequest.sortBy()).ascending());
                        case DESCENDING -> pageRequest = PageRequest.of(searchRequest.page(), searchRequest.numberOfElements(), Sort.by(searchRequest.sortBy()).descending());
                    }
                }
            } else {
                pageRequest = PageRequest.of(searchRequest.page(), searchRequest.numberOfElements(), Sort.by(searchRequest.sortBy()));
            }
        }

        return pageRequest;
    }
}
