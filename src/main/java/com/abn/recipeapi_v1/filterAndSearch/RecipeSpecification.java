package com.abn.recipeapi_v1.filterAndSearch;

import com.abn.recipeapi_v1.exception.InvalidFilterParameterException;
import com.abn.recipeapi_v1.model.Filter;
import com.abn.recipeapi_v1.model.Ingredient;
import com.abn.recipeapi_v1.model.Recipe;
import com.abn.recipeapi_v1.model.SearchRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.abn.recipeapi_v1.exception.ExceptionConstants.INVALID_FILTER_PARAMETER;

public class RecipeSpecification implements Specification<Recipe> {

    private final SearchRequest criteria;

    public RecipeSpecification(SearchRequest criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<Recipe> root, @Nullable CriteriaQuery<?> query, @Nullable CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (root == null || query == null || criteriaBuilder == null) {
            throw new ServerErrorException("Internal Server error", null);
        }

        if (!CollectionUtils.isEmpty(criteria.getFilters())) {
            for (Filter filter : criteria.getFilters()) {
                if (filter.getOperation().equalsIgnoreCase(":")) {
                    if (filter.getValue() instanceof String) {
                        if (filter.getKey().equals("recipeIngredients")) {
                            predicates.add(filterRecipesFor(filter.getValue(), true, root, query, criteriaBuilder));
                        } else {
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(
                                    root.get(filter.getKey())), "%" + String.valueOf(filter.getValue()).toLowerCase() + "%"));
                        }
                    } else {
                        predicates.add(criteriaBuilder.equal(root.get(filter.getKey()), filter.getValue()));
                    }
                } else if (filter.getOperation().equalsIgnoreCase("!:")) {
                    predicates.add(filterRecipesFor(filter.getValue(), false, root, query, criteriaBuilder));
                } else {
                    throw new InvalidFilterParameterException(INVALID_FILTER_PARAMETER);
                }
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
    }

    private Predicate filterRecipesFor(
            Object ingredientName,
            Boolean contains,
            Root<Recipe> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (ingredientName instanceof String) {
            Predicate predicate;
            String name = String.valueOf(ingredientName).toLowerCase();
            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<Recipe> subqueryRecipe = subquery.from(Recipe.class);
            Join<Recipe, Ingredient> subqueryIngredient = subqueryRecipe.join("recipeIngredients");

            if (contains) {
                subquery.select(subqueryRecipe.get("id")).where(
                        criteriaBuilder.equal(criteriaBuilder.lower(subqueryIngredient.get("ingredient").get("name")), name));

                predicate = criteriaBuilder.in(root.get("id")).value(subquery);
            } else {
                subquery.select(subqueryRecipe.get("id")).where(
                        criteriaBuilder.equal(criteriaBuilder.lower(subqueryIngredient.get("ingredient").get("name")), name));

                predicate = criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(subquery));
            }

            return predicate;
        } else {
            throw new InvalidFilterParameterException(INVALID_FILTER_PARAMETER);
        }
    }

    public static Pageable getSortingOrder(SearchRequest searchRequest) {
        int page = searchRequest.getPage() != null ? searchRequest.getPage() : 0;
        int numberOfElements = searchRequest.getNumberOfElements() != null ? searchRequest.getNumberOfElements() : 10;
        Sort sortBy = searchRequest.getSortBy() != null ? Sort.by(searchRequest.getSortBy()).ascending() : Sort.unsorted();

        return PageRequest.of(page, numberOfElements, sortBy);
    }
}
