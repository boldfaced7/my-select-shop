package com.sparta.myselectshop.folder.adapter.in.web;

import com.sparta.myselectshop.folder.application.port.in.AddFoldersCommand;
import com.sparta.myselectshop.folder.application.port.in.AddFoldersUseCase;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.myselectshop.folder.domain.Folder.Name;
import static com.sparta.myselectshop.folder.domain.Folder.UserId;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddFoldersController {

    private final AddFoldersUseCase addFoldersUseCase;

    @PostMapping("/folders")
    public void addFolders(
            @RequestBody Request request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(userDetails.getUserId(), request.folderNames());
        addFoldersUseCase.addFolders(command);
    }

    private AddFoldersCommand toCommand(String userId, List<String> folderNames) {
        return folderNames.stream()
                .map(Name::new)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        names -> new AddFoldersCommand(new UserId(userId), names)
                ));
    }

    public record Request(
            List<String> folderNames
    ) {}
}
