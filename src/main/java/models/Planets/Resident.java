package models.Planets;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Resident {

        @JsonProperty("films")
        private List<String> films;

        @JsonProperty("homeworld")
        private String homeworld;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("skin_color")
        private String skinColor;

        @JsonProperty("mass")
        private String mass;

        @JsonProperty("vehicles")
        private List<Object> vehicles;

        @JsonProperty("url")
        private String url;

        @JsonProperty("hair_color")
        private String hairColor;

        @JsonProperty("birth_year")
        private String birthYear;

        @JsonProperty("eye_color")
        private String eyeColor;

        @JsonProperty("species")
        private List<Object> species;

        @JsonProperty("starships")
        private List<Object> starships;

        @JsonProperty("name")
        private String name;

        @JsonProperty("height")
        private int height;

        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonProperty("edited")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
        private LocalDateTime edited;

        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonProperty("created")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
        private LocalDateTime created;

}