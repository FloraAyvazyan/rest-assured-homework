package models.Planets;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PlanetResponse(
        @JsonProperty("count") int count,
        @JsonProperty("next") String next,
        @JsonProperty("previous") Object previous,
        @JsonProperty("results") List<Planet> results
) {}