package com.scofield;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ScofieldAdminApplicationTests {

/*
    @Autowired
    SecurityUtils securityUtils;*/

    @Test
    void contextLoads() {
    }

    @Test
    public void password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");

        System.out.println(encode);
    }

}
