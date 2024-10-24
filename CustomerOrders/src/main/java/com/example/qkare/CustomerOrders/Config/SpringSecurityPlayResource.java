package com.example.qkare.CustomerOrders.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityPlayResource {
    @GetMapping("/csrf-token")
    public String csrfToken(HttpServletRequest request) {
        return request.getAttribute("CSRF-TOKEN").toString();
    }
}
