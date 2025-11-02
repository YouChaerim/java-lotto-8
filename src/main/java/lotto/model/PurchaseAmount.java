package lotto.model;

import static lotto.constants.LottoConstants.*;
import static lotto.constants.ErrorMessage.*;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(String input) {
        int validatedAmount = validate(input);
        this.amount = validatedAmount;
    }

    private int validate(String input) {
        int number = validateIsNumber(input);
        validateIsAboveMin(number);
        validateIsDivisibleByUnit(number);
        return number;
    }

    private int validateIsNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_NOT_A_NUMBER);
        }
    }

    private void validateIsAboveMin(int number) {
        if (number < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(PURCHASE_LESS_THAN_MINIMUM); // depth 2
        }
    }

    private void validateIsDivisibleByUnit(int number) {
        if (number % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(PURCHASE_NOT_DIVISIBLE_BY_UNIT); // depth 2
        }
    }

    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}
