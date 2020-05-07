package senac.edu.engsoft.meuproduto.controller;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ai/test")
public class ApplicationInsightsTestController {

    private static final Logger logger = LogManager.getLogger(ApplicationInsightsTestController.class);

    TelemetryClient telemetryClient;

    public ApplicationInsightsTestController(TelemetryClient telemetryClient){
        this.telemetryClient = telemetryClient;
    }

    @GetMapping("/hello")
    public String hello() {

        //track a custom event
        telemetryClient.trackEvent("Sending a custom event...");

        //trace a custom trace
        telemetryClient.trackTrace("Sending a custom trace....");

        //track a custom metric
        telemetryClient.trackMetric("custom metric", 1.0);

        //track a custom dependency
        telemetryClient.trackDependency("SQL", "Insert", new Duration(0, 0, 1, 1, 1), true);

        logger.info("Teste de info 1");
        logger.info("Teste de info 2");

        logger.error("Teste de error 1");
        logger.error("Teste de error 2");

        return "hello";
    }
}
