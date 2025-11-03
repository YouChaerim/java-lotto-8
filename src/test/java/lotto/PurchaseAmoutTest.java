package lotto;

import lotto.model.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmoutTest {
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_1000원_단위가_아닐_때() {
        assertThatThrownBy(() -> new PurchaseAmount("1500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다.")
    @Test
    void 구입_금액이_1000원_미만일_때() {
        assertThatThrownBy(() -> new PurchaseAmount("500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 0원이면 예외가 발생한다.")
    @Test
    void 구입_금액이_0원일_때() {
        assertThatThrownBy(() -> new PurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_숫자가_아닐_때() {
        assertThatThrownBy(() -> new PurchaseAmount("1000$"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
