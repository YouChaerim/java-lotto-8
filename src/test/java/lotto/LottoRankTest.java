package lotto;

import lotto.model.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @DisplayName("5개 일치, 보너스 일치 시 2등을 반환한다.")
    @Test
    void 랭크_2등_테스트() {
        LottoRank rank = LottoRank.valueOf(5, true);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 일치, 보너스 불일치 시 3등을 반환한다.")
    @Test
    void 랭크_3등_테스트() {
        LottoRank rank = LottoRank.valueOf(5, false);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("2개 일치 시 꽝을 반환한다.")
    @Test
    void 랭크_꽝_테스트() {
        LottoRank rank = LottoRank.valueOf(2, false);
        assertThat(rank).isEqualTo(LottoRank.MISS);
    }
}
