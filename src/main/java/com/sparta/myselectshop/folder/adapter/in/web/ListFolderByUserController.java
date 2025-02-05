package com.sparta.myselectshop.folder.adapter.in.web;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserQuery;
import com.sparta.myselectshop.folder.domain.Folder;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

@Slf4j(topic = "user-folder")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ListFolderByUserController {

    private final ListFoldersByUserQuery listFoldersByUserQuery;

    @GetMapping("/user-folder")
    public String listPagedFoldersByUser(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(userDetails);
        List<Response> folders = listFoldersByUserQuery
                .listFoldersByUser(command)
                .stream()
                .map(this::toResponse)
                .toList();

        model.addAttribute("folders", folders);
        return "index :: #fragment";
    }

    private ListFoldersByUserCommand toCommand(
            UserDetailsImpl userDetails
    ) {
        return new ListFoldersByUserCommand(
                new UserId(userDetails.getUserId())
        );
    }

    private Response toResponse(Folder folder) {
        return new Response(
                folder.getId(),
                folder.getName()
        );
    }

    public record Response(
            String id,
            String name
    ) {}
}
