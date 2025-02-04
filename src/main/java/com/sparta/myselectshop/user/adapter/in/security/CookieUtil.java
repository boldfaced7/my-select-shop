package com.sparta.myselectshop.user.adapter.in.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

public class CookieUtil {

    public static void addCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge
    ) {
        Cookie cookie = new Cookie(name, value);
        setCookie(response, cookie, value, "/", maxAge);
    }

    public static void deleteCookie(
            HttpServletRequest request,
            HttpServletResponse response,
            String name
    ) {
        getCookie(request, name).ifPresent(
                cookie -> setCookie(response, cookie, "", "/", 0));
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        return getCookie(request, name)
                .map(Cookie::getValue)
                .orElse(null);
    }

    private static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return Optional.empty();
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(name))
                .findAny();
    }

    private static void setCookie(
            HttpServletResponse response,
            Cookie cookie,
            String value,
            String path,
            int maxAge
    ) {
        cookie.setValue(value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
