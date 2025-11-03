package lotto.controller;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.constants.ErrorMessage;
import lotto.constants.LottoConstants;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoRank;
import lotto.model.PurchaseAmount;
import lotto.model.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = askPurchaseAmount();
        List<Lotto> lottos = LottoMachine.createLottos(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);
        WinningLotto winningLotto = askWinningLotto();
        Map<LottoRank, Integer> statistics = calculateResults(lottos, winningLotto);
        printResults(statistics, purchaseAmount);
    }

    private PurchaseAmount askPurchaseAmount() {
        while (true) {
            try {
                return getPurchaseAmountFromInput();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private PurchaseAmount getPurchaseAmountFromInput() {
        String input = inputView.inputPurchaseAmount();
        return new PurchaseAmount(input);
    }

    private WinningLotto askWinningLotto() {
        Lotto winningNumbers = askWinningNumbers();
        int bonusNumber = askBonusNumber(winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto askWinningNumbers() {
        while (true) {
            try {
                return getWinningNumbersFromInput();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto getWinningNumbersFromInput() {
        String input = inputView.inputWinningNumbers();
        return new Lotto(parseNumbers(input));
    }

    private int askBonusNumber(Lotto winningNumbers) {
        while (true) {
            try {
                return getBonusNumberFromInput(winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getBonusNumberFromInput(Lotto winningNumbers) {
        String input = inputView.inputBonusNumber();
        int bonusNumber = validateBonusNumberString(input);
        validateBonusNumberDuplication(winningNumbers, bonusNumber);
        return bonusNumber;
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_NOT_A_NUMBER);
        }
    }

    private int validateBonusNumberString(String input) {
        try {
            int number = Integer.parseInt(input.trim());
            validateBonusNumberRange(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_NOT_A_NUMBER);
        }
    }

    private void validateBonusNumberRange(int number) {
        if (number < LottoConstants.LOTTO_NUMBER_MIN || number > LottoConstants.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_INVALID_RANGE);
        }
    }

    private void validateBonusNumberDuplication(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBERS);
        }
    }

    private Map<LottoRank, Integer> calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> statistics = initializeStatisticsMap();

        for (Lotto lotto : lottos) {
            updateStatistics(statistics, lotto, winningLotto);
        }
        return statistics;
    }

    private Map<LottoRank, Integer> initializeStatisticsMap() {
        Map<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            statistics.put(rank, 0);
        }
        return statistics;
    }

    private void updateStatistics(Map<LottoRank, Integer> statistics, Lotto lotto, WinningLotto winningLotto) {
        LottoRank rank = winningLotto.match(lotto); // depth 1
        statistics.put(rank, statistics.get(rank) + 1); // depth 1
    }

    private void printResults(Map<LottoRank, Integer> statistics, PurchaseAmount purchaseAmount) {
        outputView.printStatisticsHeader();
        outputView.printRankResults(statistics);

        long totalPrize = calculateTotalPrize(statistics);
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount.getAmount());

        outputView.printProfitRate(profitRate);
    }

    private long calculateTotalPrize(Map<LottoRank, Integer> statistics) {
        long totalPrize = 0;
        for (LottoRank rank : statistics.keySet()) {
            totalPrize += rank.getPrize() * statistics.get(rank);
        }
        return totalPrize;
    }

    private double calculateProfitRate(long totalPrize, int purchaseAmount) {
        double rate = (double) totalPrize / purchaseAmount * LottoConstants.PERCENTAGE_MULTIPLIER;
        return Math.round(rate * 10.0) / 10.0;
    }
}