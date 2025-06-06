package models.PetStore;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private long id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<TagsItem> tags;

    @JsonProperty("status")
    private String status;

    public void setPhotoUrls(List<String> photoUrls){
        this.photoUrls = photoUrls;
    }

    public List<String> getPhotoUrls(){
        return photoUrls;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return category;
    }

    public void setTags(List<TagsItem> tags){
        this.tags = tags;
    }

    public List<TagsItem> getTags(){
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "photoUrls=" + photoUrls +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", category=" + category +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}