package sj.jpa.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.jpa.admin.model.network.Header;

@RequestMapping("/api")
@RestController
public class GetController {

    @GetMapping("/header")
    public Header getHeader() {
        // {"resultCode" : "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
