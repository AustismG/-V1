package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println("密码长度是："+new String("$2a$10$96JUFMiIQs4iEns3oFN/m.cZqx/psTRRKqSnveTv55rAretKBWdxa").length());
    }

}
