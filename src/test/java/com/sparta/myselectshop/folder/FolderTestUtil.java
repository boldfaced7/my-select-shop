package com.sparta.myselectshop.folder;

import com.sparta.myselectshop.folder.domain.Folder;
import org.assertj.core.api.Assertions;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static com.sparta.myselectshop.folder.domain.Folder.*;

public class FolderTestUtil {

    public final static Id ID = new Id("1");
    public final static UserId USER_ID = new UserId("1");
    public final static Name NAME = new Name("name");

    public static Folder folder(
            Id id,
            UserId userId,
            Name name
    ) {
        return generate(
                id,
                userId,
                name,
                LocalDateTime.MIN,
                LocalDateTime.MIN,
                null
        );
    }

    public static Folder setId(Folder target, Id id) {
        ReflectionTestUtils.setField(target, "id", id.value());
        return target;
    }

    public static void assertAll(
            Folder folder,
            Id id,
            UserId userId,
            Name name
    ) {
        Assertions.assertThat(folder).isNotNull();
        Assertions.assertThat(folder.getId()).isEqualTo(id.value());
        Assertions.assertThat(folder.getUserId()).isEqualTo(userId.value());
        Assertions.assertThat(folder.getName()).isEqualTo(name.value());
    }
}
