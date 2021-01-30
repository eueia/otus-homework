package stepdefs;

import org.junit.After;
import org.junit.Before;

public class Hooks {

    @Before
    public void beforeScenario(){
        System.out.println("This will run before the Scenario");
    }

    @After
    public void afterScenario(){
        System.out.println("This will run after the Scenario");
    }
}
