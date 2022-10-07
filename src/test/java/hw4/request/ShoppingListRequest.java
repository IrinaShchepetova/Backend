
package hw4.request;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "item",
        "aisle",
        "parse"
})
@Generated("jsonschema2pojo")
public class ShoppingListRequest {

    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ShoppingListRequest(String s) {
        this.item = s;
    }

    public ShoppingListRequest(String s, boolean b) {
        this.item = s;
        this.parse = b;
    }
    public ShoppingListRequest(String s, String a) {
        this.item = s;
        this.aisle = a;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @JsonProperty("parse")
    public Boolean getParse() {
        return parse;
    }

    @JsonProperty("parse")
    public void setParse(Boolean parse) {
        this.parse = parse;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}