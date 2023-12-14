package projet.gl.server;

import io.gatling.core.body.StringBody;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.internal.exceptions.util.ScenarioPrinter;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;

import io.gatling.javaapi.core.Simulation;

public class CustomerRequestSimulation extends Simulation {

    Map<String, Object> filters;
    ObjectMapper objectMapper;
    String jsonBody;
    HttpProtocolBuilder httpProtocol;

    public CustomerRequestSimulation() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("brandIds", new int[]{2});
        filters.put("colorIds", new int[]{6});
        filters.put("configurationIds", new int[]{5});
        filters.put("modelIds", new int[]{14});
        

        objectMapper = new ObjectMapper();

        try {
            jsonBody = objectMapper.writeValueAsString(filters);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        httpProtocol = HttpDsl.http
                .baseUrl("http://localhost:8080")
                .acceptHeader("application/json")
                .userAgentHeader("Gatling/Performance Test");

        ScenarioBuilder scFilter = scenario("filter")
                .exec(HttpDsl.http("filter")
                        .post("/vehicles/filters/page/0/10")
                        .body(StringBody(jsonBody))
                        .asJson());

        this.setUp(scFilter.injectOpen(constantUsersPerSec(50).during(Duration.ofSeconds(15))))
            .protocols(httpProtocol);
    }
}

