package lotto.constants;

public class ErrorMessage {
    public static final String PURCHASE_NOT_A_NUMBER = "구입 금액은 숫자여야 합니다.";
    public static final String PURCHASE_NOT_DIVISIBLE_BY_UNIT = "구입 금액은 " + LottoConstants.PURCHASE_UNIT + "원 단위여야 합니다.";
    public static final String PURCHASE_LESS_THAN_MINIMUM = "구입 금액은  " + LottoConstants.MIN_PURCHASE_AMOUNT +"원 이상이어야 합니다.";

    public static final String WINNING_NUMBERS_INVALID_COUNT = "당첨 번호는 " + LottoConstants.LOTTO_NUMBER_COUNT + "개여야 합니다.";
    public static final String WINNING_NUMBERS_NOT_A_NUMBER = "당첨 번호는 숫자여야 합니다.";
    public static final String WINNING_NUMBERS_INVALID_RANGE = "로또 번호는 " + LottoConstants.LOTTO_NUMBER_MIN + "부터 " + LottoConstants.LOTTO_NUMBER_MAX + " 사이의 숫자여야 합니다.";
    public static final String WINNING_NUMBERS_DUPLICATE = "당첨 번호에 중복된 숫자가 있습니다.";

    public static final String BONUS_NUMBER_NOT_A_NUMBER = "보너스 번호는 숫자여야 합니다.";
    public static final String BONUS_NUMBER_INVALID_RANGE = "보너스 번호는 " + LottoConstants.LOTTO_NUMBER_MIN + "부터 " + LottoConstants.LOTTO_NUMBER_MAX + " 사이의 숫자여야 합니다.";
    public static final String BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBERS = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public static final String LOTTO_INVALID_SIZE = "로또 번호는 " + LottoConstants.LOTTO_NUMBER_COUNT + "개여야 합니다.";
    public static final String LOTTO_DUPLICATE_NUMBERS = "로또 번호에 중복된 숫자가 있습니다.";
    public static final String LOTTO_NUMBER_INVALID_RANGE = "로또 번호는 " + LottoConstants.LOTTO_NUMBER_MIN + "부터 " + LottoConstants.LOTTO_NUMBER_MAX + " 사이의 숫자여야 합니다.";
}
