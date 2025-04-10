package br.com.wareysis.core.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {

    public static Timestamp dhUpdate() {

        return Timestamp.valueOf(LocalDateTime.now());
    }

}
