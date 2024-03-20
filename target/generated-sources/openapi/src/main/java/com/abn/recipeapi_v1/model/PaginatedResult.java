package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaginatedResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-20T12:28:10.528866+01:00[Europe/Amsterdam]")
public class PaginatedResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer page;

  private Integer perPage;

  private Boolean hasNext;

  @Valid
  private List<Object> results;

  public PaginatedResult page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
  */
  
  @Schema(name = "page", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public PaginatedResult perPage(Integer perPage) {
    this.perPage = perPage;
    return this;
  }

  /**
   * Get perPage
   * @return perPage
  */
  
  @Schema(name = "per_page", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("per_page")
  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public PaginatedResult hasNext(Boolean hasNext) {
    this.hasNext = hasNext;
    return this;
  }

  /**
   * Get hasNext
   * @return hasNext
  */
  
  @Schema(name = "has_next", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("has_next")
  public Boolean getHasNext() {
    return hasNext;
  }

  public void setHasNext(Boolean hasNext) {
    this.hasNext = hasNext;
  }

  public PaginatedResult results(List<Object> results) {
    this.results = results;
    return this;
  }

  public PaginatedResult addResultsItem(Object resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
  */
  
  @Schema(name = "results", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("results")
  public List<Object> getResults() {
    return results;
  }

  public void setResults(List<Object> results) {
    this.results = results;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginatedResult paginatedResult = (PaginatedResult) o;
    return Objects.equals(this.page, paginatedResult.page) &&
        Objects.equals(this.perPage, paginatedResult.perPage) &&
        Objects.equals(this.hasNext, paginatedResult.hasNext) &&
        Objects.equals(this.results, paginatedResult.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, perPage, hasNext, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaginatedResult {\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    perPage: ").append(toIndentedString(perPage)).append("\n");
    sb.append("    hasNext: ").append(toIndentedString(hasNext)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

