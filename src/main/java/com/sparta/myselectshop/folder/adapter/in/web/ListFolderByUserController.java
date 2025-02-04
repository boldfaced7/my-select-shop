package com.sptitlemyselectshop.folder.adapter.in.web;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserQuery;
import com.sparta.myselectshop.folder.domain.Folder;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ListFolderByUserController {

    private final ListFoldersByUserQuery listFoldersByUserQuery;

    @GetMapping("/user-folder")
    public String listPagedFoldersByUser(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        var command = toCommand(userDetails, page, size, sortBy, isAsc);
        var folders = listFoldersByUserQuery
                .listFoldersByUser(command)
                .map(this::toResponse);

        model.addAttribute("folders", folders);
        return "index :: #fragment";
    }

    private ListFoldersByUserCommand toCommand(
            UserDetailsImpl userDetails,
            int page,
            int size,
            String sortBy,
            boolean isAsc
    ) {
        return new ListFoldersByUserCommand(
                new UserId(userDetails.getUserId()),
                page,
                size,
                sortBy,
                isAsc
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
