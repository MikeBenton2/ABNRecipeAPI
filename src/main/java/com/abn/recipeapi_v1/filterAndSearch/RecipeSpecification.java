package com.abn.recipeapi_v1;

import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.RecipeIngredient;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

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

        for (Filter filter : criteria.getFilters()) {
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

        if(searchRequest.getPage() != null && searchRequest.getNumberOfElements() != null) {
            if(searchRequest.getSortBy() != null) {
                if(searchRequest.getOrderBy() != null) {
                    switch (searchRequest.getOrderBy()) {
                        case ASCENDING -> pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getNumberOfElements(), Sort.by(searchRequest.getSortBy()).ascending());
                        case DESCENDING -> pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getNumberOfElements(), Sort.by(searchRequest.getSortBy()).descending());
                    }
                }
            } else {
                pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getNumberOfElements(), Sort.by(searchRequest.getSortBy()));
            }
        }

        return pageRequest;
    }
}
