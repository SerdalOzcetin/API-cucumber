package stepdefinitions;

import com.google.gson.JsonObject;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import pojos.Pojo_RegisterCustomer;
import utulities.Authentication;
import utulities.ConfigReader;

import static io.restassured.RestAssured.given;

public class CoomonAPI {

       public static String fullPath;
    Pojo_RegisterCustomer requestBody;

    JSONObject requestBodyforLogin;
    @Given("API user sets the {string} path parameters")
    public void apı_user_sets_the_path_parameters(String rawPaths) {


        String[] paths = rawPaths.split("/");

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i <paths.length ; i++) {

            String key = "pp"+i;
            String value = paths[i].trim();

            Hooks.spec.pathParams(key,value);
            tempPath.append(key+"}/{");

        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString();
    }


    @And("Prepare the Request Body {string},{string},{string},{string},{string},{string},{string},{string},{string} that ıs needed for Register Customer")
    public void prepareTheRequestBodyThatIsNeededForRegisterCustomer(String first_name, String last_name, String username, String email, String password, String password_confirmation, String user_type, String phone, String referral_code) {

       requestBody = new Pojo_RegisterCustomer(first_name,last_name,username,email,password,password_confirmation,user_type,phone,referral_code);

    }

    @And("Send Post Request for Register Customer")
    public void sendPostRequestForRegisterCustomer() {

        Response response = given()
                .spec(Hooks.spec)
                .contentType(ContentType.JSON).header("Accept","application/json")
                .when().body(requestBody)
                .post(fullPath);

        response.prettyPrint();

    }

    @And("Prepare the Request Body {string},{string} that ıs needed for Login")
    public void prepareTheRequestBodyThatIsNeededForLogin(String email, String password) {


        requestBodyforLogin = new JSONObject();
        requestBodyforLogin.put("email", ConfigReader.getProperty(email));
        requestBodyforLogin.put("password",ConfigReader.getProperty(password));


    }

    @And("Send Post Request for Login")
    public void sendPostRequestForLogin() {

       Response response = given()
                .spec(Hooks.spec)
                .contentType(ContentType.JSON)
                .body(requestBodyforLogin.toString()).header("Accept","application/json")
                .post(fullPath);

        response.prettyPrint();



        Authentication.generateToken();
    }

    @Then("Send Get request for allCountries")
    public void sendGetRequestForAllCountries() {

        Response response = given()
                .spec(Hooks.spec)
                .headers("Authorization","Bearer "+Hooks.token)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when().get(fullPath);

        response.prettyPrint();

    }
}
