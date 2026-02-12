package domains;

import java.util.List;

public class Money {
    private final int amount;
    private final static int LOTTO_PRICE = 1000;

    public Money(int amount) {
        validate(amount);

        this.amount = amount;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }

        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format("구입 금액은 최소 %d원 이상이어야 합니다.", LOTTO_PRICE));
        }
    }

    public Integer availableLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public Float calculateRate(List<Rank> rankList) {
        if (amount == 0) return (float) 0;

        long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return (float) totalWinningMoney / amount;
    }
}
