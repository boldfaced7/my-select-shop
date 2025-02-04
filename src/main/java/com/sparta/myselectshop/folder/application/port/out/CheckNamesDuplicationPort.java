package com.sparta.myselectshop.folder.application.port.out;

import java.util.List;

import static com.sparta.myselectshop.folder.domain.Folder.Name;
import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public interface CheckNamesDuplicationPort {
    boolean isDuplicated(UserId id, List<Name> names);
}
