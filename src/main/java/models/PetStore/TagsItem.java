package models.PetStore;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagsItem{

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private long id;

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

    @Override
    public String toString() {
        return "TagsItem{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}