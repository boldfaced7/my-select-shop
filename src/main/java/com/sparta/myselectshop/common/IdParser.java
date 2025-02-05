package com.sparta.myselectshop.common;

public class IdParser {
    public static Long parseLong(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
    }
}
