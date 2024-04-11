package com.abn.recipeapi_v1;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SearchRequest {

    public enum OrderBy {
        ASCENDING,
        DESCENDING
    }

    private List<Filter> filters;
    private Integer page;
    private Integer numberOfElements;
    private String sortBy;
    private OrderBy orderBy;
    public SearchRequest(
            List<Filter> filters,
            Integer page,
            Integer numberOfElements,
            String sortBy,
            OrderBy orderBy
    ) {
        this.filters = filters;
        this.page = page;
        this.numberOfElements = numberOfElements;
        this.sortBy = sortBy;
        this.orderBy = orderBy;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public String getSortBy() {
        return sortBy;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }
}
