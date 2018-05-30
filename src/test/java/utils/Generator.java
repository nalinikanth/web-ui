package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;

public class Generator {

    public static String randomShortInt() {
        return randomInt(2);
    }

    public static String randomShortText() {
        return randomText(10);
    }

    public static String randomInt(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static String randomText(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

}
