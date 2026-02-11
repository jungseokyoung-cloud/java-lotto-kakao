package domains;

import java.util.List;

public class Money {
    private int amount;
    private final static int lottoCost = 1000;

    public Money(Integer amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        this.amount = amount;
    }

    public Integer availableLottoCount() {
        return amount / lottoCost;
    }

    public Float calculateRate(List<Rank> rankList) {
        if (amount == 0) return (float) 0;

        long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return (float) totalWinningMoney / amount;
    }
}
