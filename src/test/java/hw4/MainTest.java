package hw4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw4.request.ShoppingListRequest;
import hw4.response.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {
    ObjectMapper objectMapper = new ObjectMapper();
    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;


    @BeforeEach
    void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";
        requestSpec = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .addQueryParam("apiKey", "60437a7ac1604ddeb0e36291bd66c938" )
                .addQueryParam("hash", "7f0e48e28b9f70ae0eb5fd55f9c364171fb1b842")
                .addQueryParam("username", "your-users-name441")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Headers", "Authorization, UserId, Hash, Name, Password, Accept, Accept-Language, Content-Language, Content-Type, Cache-Control, Origin, X-Requested-With")
                .build();

    }
    @DisplayName("Add to Shopping List")
    @ParameterizedTest
    @MethodSource("addItemSource")
    @Order(1)
    void addToShoppingList(ShoppingListRequest item) throws JsonProcessingException {

        RestAssured.given()
                .spec(requestSpec)
                .body(objectMapper.writeValueAsString(item))
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name441/shopping-list/items")
                .then()
                .spec(responseSpec);
    }
    static Stream<ShoppingListRequest> addItemSource() {
        return Stream.of(
                new ShoppingListRequest("1 package baking powder"),
                new ShoppingListRequest("1 package baking powder", true),
                new ShoppingListRequest("pizza", true)
        );
    }

    private static  GetShoppingList getToShoppingList;


    @DisplayName("Get to Shopping List")
    @Test
    @Order(2)
    void getToShoppingList(){
        getToShoppingList = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("https://api.spoonacular.com/mealplanner/your-users-name441/shopping-list")
                .then()
                .spec(responseSpec)
                .extract()
                .body().as(GetShoppingList.class);
        assertThat(getToShoppingList.getCost(), equalTo(49.81));
        assertThat(getToShoppingList.getStartDate(), notNullValue());
        assertThat(getToShoppingList.getEndDate(), notNullValue());
        assertThat(getToShoppingList.getAisles().stream()
                .filter(s-> s.getAisle().equals("1 package baking powder")).count(), equalTo(1L));
        assertThat(getToShoppingList.getAisles().stream()
                .filter(s-> s.getAisle().equals("pizza")).count(), equalTo(1L)) ;
    }

    private static  Item item;

    @DisplayName("Delete from Shopping List")
    @ParameterizedTest
    @MethodSource("deleteItemSource")
    @Order(3)
    void deleteToShoppingList(Item item) {
        var response = RestAssured.given()
                .spec(requestSpec)
                .pathParam("id", item.getId())
                .when()
                .delete("https://api.spoonacular.com/mealplanner/your-users-name441/shopping-list/items/{id}")
                .then()
                .spec(responseSpec)
                .extract()
                .body().as(Status.class);
        assertThat(response.getStatus(), equalTo("success"));

    }

    static Stream<Item> deleteItemSource() {
        return getToShoppingList
                .getAisles().stream()
                .flatMap(a -> a.getItems().stream());
    }



}
