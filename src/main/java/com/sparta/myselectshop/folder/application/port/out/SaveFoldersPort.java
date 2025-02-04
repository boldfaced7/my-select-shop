package com.sparta.myselectshop.folder.application.port.out;

import com.sparta.myselectshop.folder.domain.Folder;

import java.util.List;

public interface SaveFoldersPort {
    List<Folder> saveAll(List<Folder> folders);
}
