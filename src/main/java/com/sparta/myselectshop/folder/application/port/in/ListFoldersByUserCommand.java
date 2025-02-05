package com.sparta.myselectshop.folder.application.port.in;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public record ListFoldersByUserCommand(
        UserId userId
) {}
