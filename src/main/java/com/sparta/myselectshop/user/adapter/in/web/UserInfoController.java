package com.sparta.myselectshop.user.adapter.in.web;

import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import com.sparta.myselectshop.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserInfoController {

    @GetMapping("/user-info")
    public ResponseEntity<Response> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        var username = userDetails.getUsername();
        var admin = (userDetails.getUser().getRole() == UserRole.ADMIN);

        return ResponseEntity.ok(new Response(username, admin));
    }


    public record Response(
            String username,
            boolean isAdmin
    ) {}
}
