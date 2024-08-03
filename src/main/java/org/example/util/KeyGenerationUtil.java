package org.example.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * The type KeyGenerationUtil
 *
 * @author nadeem
 * Date : 03/08/24
 */
@UtilityClass
public class KeyGenerationUtil {
    public static String generate() {
        return UUID.randomUUID().toString();
    }

}
