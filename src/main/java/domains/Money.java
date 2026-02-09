package domains;

public class Money {
    private int amount;
    private final int lottoCost = 1000;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }
        this.amount = amount;
    }

    public int availableLottoCount() {
        return amount / lottoCost;
    }
}
