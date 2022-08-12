package com.example.task.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.task.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 */
@Component
public class JWTUtil {
    private static final String secret = "@gzy03116";

    public static String createToken(Employee employee) {
        Map<String, String> map = new HashMap<>();
        map.put("id", employee.getEeId().toString());
        map.put("role", employee.getRole().toString());

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        return JWT.create()
                .withClaim("employee",map)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secret));
    }

    public Employee getEmployeeFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        Map<String, Object> map = decodedJWT.getClaim("employee").asMap();
        Employee employee = new Employee();
        employee.setEeId(Long.parseLong(map.get("id").toString()));
        employee.setRole(Integer.parseInt(map.get("role").toString()));
        return employee;
    }
}
