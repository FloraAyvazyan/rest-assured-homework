package models.PetStoreXML;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@XmlRootElement(name = "Pet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "category")
    private Category category;

    @XmlElementWrapper(name = "photoUrls")
    @XmlElement(name = "photoUrl")
    private List<String> photoUrls;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<TagsItem> tags;

    @XmlElement(name = "status")
    private String status;

}