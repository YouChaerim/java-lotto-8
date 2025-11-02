package lotto.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.LottoRank;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String RANK_RESULT_FORMAT_DEFAULT = "%d개 일치 (%s원) - %d개";
    private static final String RANK_RESULT_FORMAT_SECOND = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";

    private static final NumberFormat numberFormatter = NumberFormat.getNumberInstance();

    public void printPurchaseCount(int count) {
        System.out.printf(PURCHASE_COUNT_MESSAGE + "\n", count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString()); // Lotto.java의 toString() 활용
        }
    }

    public void printStatisticsHeader() {
        System.out.println(STATISTICS_HEADER);
    }

    public void printRankResults(Map<LottoRank, Integer> rankCounts) {
        // 3주 차 요구사항: 5등부터 1등까지 순서대로 출력
        printSingleRank(LottoRank.FIFTH, rankCounts.getOrDefault(LottoRank.FIFTH, 0));
        printSingleRank(LottoRank.FOURTH, rankCounts.getOrDefault(LottoRank.FOURTH, 0));
        printSingleRank(LottoRank.THIRD, rankCounts.getOrDefault(LottoRank.THIRD, 0));
        printSingleRank(LottoRank.SECOND, rankCounts.getOrDefault(LottoRank.SECOND, 0));
        printSingleRank(LottoRank.FIRST, rankCounts.getOrDefault(LottoRank.FIRST, 0));
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE + "\n", profitRate);
    }

    private void printSingleRank(LottoRank rank, int count) {
        String prizeString = numberFormatter.format(rank.getPrize());

        if (rank.isRequiresBonus()) {
            System.out.printf(RANK_RESULT_FORMAT_SECOND + "\n",
                    rank.getMatchCount(), prizeString, count);
            return;
        }

        System.out.printf(RANK_RESULT_FORMAT_DEFAULT + "\n",
                rank.getMatchCount(), prizeString, count);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}