package senac.edu.engsoft.meuproduto.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", glue = "senac.edu.engsoft.meuproduto.cucumber", features = "./home.feature")
public class HomeTests {
}
