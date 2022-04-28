package mk.ukim.finki.db.project.utils;

import java.math.BigDecimal;

public class PriceToCryptoConverter {

    public static Double denarToEthereum(Double denar) {
        return denar * 0.00000595D;
    }

    public static String denarToGwei(Double denar) {
        return BigDecimal.valueOf(denarToEthereum(denar)).multiply(new BigDecimal("1000000000000000000")).toString();
    }
}
