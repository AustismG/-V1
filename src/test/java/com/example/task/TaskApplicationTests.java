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
        System.out.println("密码长度是：" + new String("$2a$10$96JUFMiIQs4iEns3oFN/m.cZqx/psTRRKqSnveTv55rAretKBWdxa").length());
        System.out.println("比较的结果是：");
        System.out.println("$2a$10$3YIQh8fbz20pCJU3tOCNTu.F5uroPCfgw92E/M2j85QTf7wyB30me".equals(passwordEncoder.encode("000000")));
    }
    @Test
    void deCoder(){

    }
}
