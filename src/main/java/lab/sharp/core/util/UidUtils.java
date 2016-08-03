package lab.sharp.core.util;

import java.util.UUID;

public class UidUtils {

    public static String UID() {
        return UUID.randomUUID().toString();
    }
}
