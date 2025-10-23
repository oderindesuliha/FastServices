package org.group6.fastservices.utils;

import java.security.SecureRandom;

public class OrganizationCodeGenerator {

    public static String generateCode() {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "@$%#";
        String characters = alphabets+digits+symbols;
        SecureRandom random = new SecureRandom();
        StringBuilder tokenBuilder = new StringBuilder();
        for (int count = 0; count < 6; count++) {
            int charIndex = random.nextInt(characters.length());
            tokenBuilder.append(characters.charAt(charIndex));
        }
        return tokenBuilder.toString();
    }
}
