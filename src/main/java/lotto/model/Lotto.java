package lotto.model;

import java.util.*;

import static lotto.constants.LottoConstants.*;
import static lotto.constants.ErrorMessage.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_INVALID_SIZE);
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_DUPLICATE_NUMBERS);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumberInRange(number); // depth 2
        }
    }

    private void validateNumberInRange(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) { // depth 1
            throw new IllegalArgumentException(LOTTO_NUMBER_INVALID_RANGE); // depth 2
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int countMatches(Lotto otherLotto) {
        int count = 0;
        for (int number : numbers) {
            count += otherLotto.countIfPresent(number); // depth 2
        }
        return count;
    }

    private int countIfPresent(int number) {
        if (numbers.contains(number)) { // depth 1
            return 1; // depth 2
        }
        return 0; // 'else 금지' 규칙 준수
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
