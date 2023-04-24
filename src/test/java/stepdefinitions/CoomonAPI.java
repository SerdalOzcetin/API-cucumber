package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;

public class CoomonAPI {


    @Given("API user sets the {string} path parameters")
    public void apı_user_sets_the_path_parameters(String rawPaths) {


        String[] paths = rawPaths.split("/");

        StringBuilder tempPath = new StringBuilder("{");

        for (int i = 0; i <paths.length ; i++) {

            String key = "pp"+i;
            String value = paths[i].trim();

            Hooks.spec.pathParams(key,value);
            tempPath.append(key+"}/{");

        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
    }

}
