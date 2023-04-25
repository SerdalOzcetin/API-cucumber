package utulities;

import hooks.Hooks;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;


    public static String generateToken(){

        spec  = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","login");

            Map<String, Object> dataCredentials = new HashMap<>();
            dataCredentials.put("email",ConfigReader.getProperty("email"));
            dataCredentials.put("password", ConfigReader.getProperty("password"));

            Response response  = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept","application/json")
                    .when()
                    .body(dataCredentials).post("/{pp1}/{pp2}");

        JsonPath responsoJson = response.jsonPath();

        String token =responsoJson.getString("token");

        return token;
    }
}
