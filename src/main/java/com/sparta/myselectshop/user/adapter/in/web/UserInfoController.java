package com.sparta.myselectshop.user.adapter.in.web;

import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import com.sparta.myselectshop.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "UserInfo")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserInfoController {

    @GetMapping("/user-info")
    public Response getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        var username = userDetails.getUser().getUsername();
        var admin = (userDetails.getUser().getRole() == UserRole.ADMIN);
        return new Response(username, admin);
    }


    public record Response(
            String username,
            boolean isAdmin
    ) {}
}
