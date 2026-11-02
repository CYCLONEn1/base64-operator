package com.example.base64operator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@RestController
@RequestMapping("/api")
public class Base64OperatorController {

    @PostMapping("/encode")
    public String encoder(@RequestParam String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping("/decode")
    public String decoder(@RequestParam String text) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(text);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return "解密失败：不是合法的Base64字符串";
        }
    }
}
