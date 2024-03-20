package com.abn.recipeapi_v1.model;

import java.net.URI;
import java.util.Objects;
import com.abn.recipeapi_v1.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * GetRecipes200Response
 */

@JsonTypeName("getRecipes_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-20T12:28:10.528866+01:00[Europe/Amsterdam]")
public class GetRecipes200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer page;

  private Integer perPage;

  private Boolean hasNext;

  @Valid
  private List<@Valid Recipe> results;

  public GetRecipes200Response page(Integer page) {
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

  public GetRecipes200Response perPage(Integer perPage) {
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

  public GetRecipes200Response hasNext(Boolean hasNext) {
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

  public GetRecipes200Response results(List<@Valid Recipe> results) {
    this.results = results;
    return this;
  }

  public GetRecipes200Response addResultsItem(Recipe resultsItem) {
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
  @Valid 
  @Schema(name = "results", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("results")
  public List<@Valid Recipe> getResults() {
    return results;
  }

  public void setResults(List<@Valid Recipe> results) {
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
    GetRecipes200Response getRecipes200Response = (GetRecipes200Response) o;
    return Objects.equals(this.page, getRecipes200Response.page) &&
        Objects.equals(this.perPage, getRecipes200Response.perPage) &&
        Objects.equals(this.hasNext, getRecipes200Response.hasNext) &&
        Objects.equals(this.results, getRecipes200Response.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, perPage, hasNext, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetRecipes200Response {\n");
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

