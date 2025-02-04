package com.sparta.myselectshop.folder.application.port.in;

import java.util.List;

import static com.sparta.myselectshop.folder.domain.Folder.Name;
import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public record AddFoldersCommand(
        UserId userId,
        List<Name> names
) {}
