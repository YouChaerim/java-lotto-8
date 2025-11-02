package lotto.model;

public enum LottoRank {
    FIRST(6, 2_000_000_000L, false),
    SECOND(5, 30_000_000L, true),
    THIRD(5, 1_500_000L, false),
    FOURTH(4, 50_000L, false),
    FIFTH(3, 5_000L, false),
    MISS(0, 0L, false);

    private final int matchCount;
    private final long prize;
    private final boolean requiresBonus;

    LottoRank(int matchCount, long prize, boolean requiresBonus) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.requiresBonus = requiresBonus;
    }

    public static LottoRank valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }
        if (matchCount == THIRD.matchCount && !bonusMatch) {
            return THIRD;
        }
        if (matchCount == FOURTH.matchCount) {
            return FOURTH;
        }
        if (matchCount == FIFTH.matchCount) {
            return FIFTH;
        }
        if (matchCount == SECOND.matchCount && bonusMatch) {
            return SECOND;
        }

        return MISS;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isRequiresBonus() {
        return requiresBonus;
    }
}