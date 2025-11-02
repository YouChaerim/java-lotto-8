package lotto.model;

import static lotto.constants.ErrorMessage.*;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBERS);
        }
    }

    public LottoRank match(Lotto userLotto) {
        int matchCount = userLotto.countMatches(winningNumbers);
        boolean bonusMatch = userLotto.contains(bonusNumber);

        return LottoRank.valueOf(matchCount, bonusMatch);
    }
}