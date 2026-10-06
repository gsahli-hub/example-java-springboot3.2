package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.HexFormat;

@RestController
public class VulnController {

    // Correction XSS : la saisie est échappée avant d'être renvoyée en HTML
    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    public String hello(@RequestParam String name) {
        return "<h1>Bonjour " + HtmlUtils.htmlEscape(name) + "</h1>";
    }

    // Correction injection de commande : plus de shell, saisie validée
    @GetMapping("/ping")
    public String ping(@RequestParam String host) throws Exception {
        if (!host.matches("^[a-zA-Z0-9.-]{1,253}$")) {
            return "Hôte invalide";
        }
        return InetAddress.getByName(host).isReachable(2000) ? "joignable" : "injoignable";
    }

    // Correction algorithme faible : SHA-256 à la place de MD5
    @GetMapping("/hash")
    public String hash(@RequestParam String pwd) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return HexFormat.of().formatHex(md.digest(pwd.getBytes()));
    }
}