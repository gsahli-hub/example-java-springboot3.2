package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.util.HexFormat;

@RestController
public class VulnController {

    // Faille 1 : XSS réfléchi (entrée utilisateur renvoyée telle quelle en HTML)
    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    public String hello(@RequestParam String name) {
        return "<h1>Bonjour " + name + "</h1>";
    }

    // Faille 2 : injection de commande OS
    @GetMapping("/ping")
    public String ping(@RequestParam String host) throws Exception {
        Process p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "ping -c 1 " + host});
        return new String(p.getInputStream().readAllBytes());
    }

    // Faille 3 : algorithme de hachage faible (MD5)
    @GetMapping("/hash")
    public String hash(@RequestParam String pwd) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return HexFormat.of().formatHex(md.digest(pwd.getBytes()));
    }
}