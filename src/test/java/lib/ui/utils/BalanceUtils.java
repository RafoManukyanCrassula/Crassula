package lib.ui.utils;

public class BalanceUtils {

    public static double calculateGreaterAmount(String balanceText) {
        String numericBalanceText = balanceText
                .replace("Balance:", "")
                .replace(",", "")
                .replaceAll("[a-zA-Z]+", "")
                .trim();

        double currentBalance;
        try {
            currentBalance = Double.parseDouble(numericBalanceText);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse balance: " + balanceText, e);
        }

        return currentBalance + 10.0;
    }
}