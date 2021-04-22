package pdp.uz.lesson6.component;

import org.springframework.stereotype.Component;
import java.util.Random;
import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public class SerialNumberGenerator {
    private static final Random GENERATOR = new Random();

    private static final String[] IMEI_REPORTING_BODY_IDS = {"01", "10", "30", "33", "35", "44",
            "45", "49", "50", "51", "52", "53", "54", "86", "91", "98", "99"};

    public static int sumDigits(int number) {
        int a = 0;
        while (number > 0) {
            a = a + number % 10;
            number = number / 10;
        }
        return a;
    }

    public static String generateImei() {
        String first14 = format("%s%.12s",
                IMEI_REPORTING_BODY_IDS[GENERATOR.nextInt(IMEI_REPORTING_BODY_IDS.length)],
                format(ENGLISH, "%012d", abs(GENERATOR.nextLong())));

        int sum = 0;

        for (int i = 0; i < first14.length(); i++) {
            int c = Character.digit(first14.charAt(i), 10);
            sum += (i % 2 == 0 ? c : sumDigits(c * 2));
        }

        int finalDigit = (10 - (sum % 10)) % 10;

        return first14 + finalDigit;
    }


}
