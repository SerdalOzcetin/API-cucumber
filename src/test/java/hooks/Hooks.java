package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utulities.Authentication;
import utulities.ConfigReader;
import utulities.Driver;

public class Hooks {
    /*
    Hooks : Her bir Scenario yada Scenario Outline dan ONCE yada SONRA calismasini istedigim metotlar konur
    @Before ve @After metotlarini icerir
    !!!!!!!!!!!Burda onemli olan raporlama isleminin ekran goruntusuyle birlikte Hooks yardimiyla yapilmasidir
     */

    public static RequestSpecification spec;
    public static String token;


    @Before(order = 0)
    public void setUpAPI(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

    }



    @Before(order = 1)
    public void beforeGenerateToken(){
        token = Authentication.generateToken();
    }
}