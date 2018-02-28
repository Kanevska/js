package entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {

    @JsonProperty("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
