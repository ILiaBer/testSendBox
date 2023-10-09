package utils;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Tools {

    public static <T extends Enum<?>> T getRandomEnumExceptOne(Enum<?> excepted) {
        return (T) stream(excepted.getClass().getEnumConstants())
                .filter(e -> !e.equals(excepted)).collect(Collectors.toList())
                .get(new SecureRandom().nextInt(excepted.getClass().getEnumConstants().length - 1));
    }

    public static boolean compareImages(File file1, URL url2) {
        try {
            BufferedImage image1 = ImageIO.read(file1);
            BufferedImage image2 = ImageIO.read(url2);
            if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
                return false;
            }
            for (int x = 0; x < image1.getWidth(); x++) {
                for (int y = 0; y < image1.getHeight(); y++) {
                    if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @SneakyThrows
    public static Object getRandomClassObj(Class<?> classEx) {
        SecureRandom random = new SecureRandom();
        int x = random.nextInt(classEx.getDeclaredFields().length);
        return classEx.getDeclaredFields()[x].get(0);
    }

    public static Double calculateTax(Double price) {
        String formattedPrice = new DecimalFormat("#.00").format(price * 0.08);
        formattedPrice = formattedPrice.replace(",", ".");
        return Double.parseDouble(formattedPrice);
    }

    public static Double calculateTotal(Double price) {
        String formattedPrice = new DecimalFormat("#.00").format(price * 1.08);
        formattedPrice = formattedPrice.replace(",", ".");
        return Double.parseDouble(formattedPrice);
    }
}