package projet.gl.server;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String test() {
        return "{\"message\":\"it works\"}";
    }
    
}
