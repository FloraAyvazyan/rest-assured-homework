package models.Planets;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public record Planet(
        @JsonProperty("name") String name,
        @JsonProperty("rotation_period") int rotation_period,
        @JsonProperty("orbital_period") String  orbital_period,
        @JsonProperty("diameter") String  diameter,
        @JsonProperty("climate") String climate,
        @JsonProperty("gravity") String gravity,
        @JsonProperty("terrain") String terrain,
        @JsonProperty("surface_water") String  surface_water,
        @JsonProperty("population") String population,
        @JsonProperty("residents") List<String> residents,
        @JsonProperty("films") List<String> films,


        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
        @JsonProperty("created") LocalDateTime created,


        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
        @JsonProperty("edited") LocalDateTime edited,

        @JsonProperty("url") String url

){}