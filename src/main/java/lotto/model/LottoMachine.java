package lotto.model;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

import static lotto.constants.LottoConstants.*;

public class LottoMachine {

    public static List<Lotto> createLottos(PurchaseAmount purchaseAmount) {
        int lottoCount = purchaseAmount.getLottoCount();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                RANDOM_START_NUMBER,
                RANDOM_END_NUMBER,
                RANDOM_NUMBER_COUNT
        );
        return new Lotto(numbers);
    }
}