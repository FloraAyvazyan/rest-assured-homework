package models.PetStoreXML;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@XmlRootElement(name = "TagsItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class TagsItem{
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
}