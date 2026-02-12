import domains.Money;
import domains.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domains.Rank.FIRST;
import static domains.Rank.SECOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {
    @Test
    public void 구입_금액에_해당하는_로또_발급_수량을_계산한다() {
        Money money = new Money(14243);

        assertEquals(14, money.availableLottoCount());
    }

    @Test
    public void 당첨_결과를_바탕으로_총_수익률을_계산한다() {
        int value = 14000;
        Money money = new Money(value);
        List<Rank> rankList = List.of(FIRST, SECOND);
        int sum = FIRST.getWinningMoney() + SECOND.getWinningMoney();
        assertEquals((float) sum / value, money.calculateRate(rankList));
    }

    @Test
    public void 구입_금액이_0원일_때_수익률은_0을_반환한다() {
        int value = 0;
        Money money = new Money(value);
        List<Rank> rankList = List.of(FIRST, SECOND);
        assertEquals(0, money.calculateRate(rankList));
    }
}
