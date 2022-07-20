package playground.spring.security.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panos Bariamis (pbaris)
 */
@RestController
@RequestMapping("/rest")
public class TestRestController {

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of(
            "name", "Test",
            "code", "Lala"
        );
    }
}
