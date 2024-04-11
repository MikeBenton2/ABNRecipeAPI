package com.abn.recipeapi_v1.filterAndSearch;

import java.util.List;

public record SearchRequest(List<Filter> filters, Integer page, Integer numberOfElements, String sortBy,
                            com.abn.recipeapi_v1.filterAndSearch.SearchRequest.OrderBy orderBy) {

    public enum OrderBy {
        ASCENDING,
        DESCENDING
    }

}
