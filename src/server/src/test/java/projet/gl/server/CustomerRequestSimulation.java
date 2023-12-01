package projet.gl.server;

import io.gatling.core.body.StringBody;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

import java.time.Duration;

import org.mockito.internal.exceptions.util.ScenarioPrinter;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;

import io.gatling.javaapi.core.Simulation;


public class CustomerRequestSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol = HttpDsl.http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")
    .userAgentHeader("Gatling/Performance Test");

  ScenarioBuilder scn = scenario("countByBrand")
    .exec(HttpDsl.http("Count By Brand") 
      .get("/vehicles/countByBrand"));

  ScenarioBuilder scLogin = scenario("login")
                .exec(HttpDsl.http("login")
                        .post("/auth/login")
                        .body(StringBody("{\"email\":\"ahmed@gmail.com\",\"password\":\"12345678\"}"))
                        .asJson());

  ScenarioBuilder scFilter = scenario("filter")
                .exec(HttpDsl.http("filter")
                .post("/vehicles/filters/page/0/10")
                .body(StringBody("{}"))
                .asJson());

  ScenarioBuilder scAddBrand = scenario("addBrand")
                .exec(HttpDsl.http("addBrand")
                .post("/brands")
                .body(StringBody("{\"name\":\"MERCEDES\"}"))
                .asJson());


    public CustomerRequestSimulation() {
      this.setUp(
        scn.injectOpen(constantUsersPerSec(50).during(Duration.ofSeconds(15))),
        scLogin.injectOpen(constantUsersPerSec(30).during(Duration.ofSeconds(20))) ,
        scFilter.injectOpen(constantUsersPerSec(50).during(Duration.ofSeconds(15))),
        scAddBrand.injectOpen(constantUsersPerSec(1).during(Duration.ofSeconds(1)))
      )          .protocols(httpProtocol);
      }


}
