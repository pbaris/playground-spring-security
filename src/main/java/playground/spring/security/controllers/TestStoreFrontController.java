package playground.spring.security.controllers;

import java.util.Map;
import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panos Bariamis (pbaris)
 */
@RestController
@RequestMapping("/api-sf")
public class TestStoreFrontController {

//    @RolesAllowed("VIEW")
    @PreAuthorize("permitAll()")
    @GetMapping("/shell")
    public Map<String, String> test() {
        return Map.of(
            "name", "Test",
            "code", "Lala"
        );
    }
}
