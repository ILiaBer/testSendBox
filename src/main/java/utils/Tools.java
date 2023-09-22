package utils;

import java.security.SecureRandom;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Tools {

    public static <T extends Enum<?>> T getRandomEnumExceptOne(Enum<?> excepted) {
        return (T) stream(excepted.getClass().getEnumConstants())
                .filter(e -> !e.equals(excepted)).collect(Collectors.toList())
                .get(new SecureRandom().nextInt(excepted.getClass().getEnumConstants().length-1));
    }
}
