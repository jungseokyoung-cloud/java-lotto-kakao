import domains.Money;
import domains.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domains.Rank.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    public void 구입_금액이_1000원_이하일_때_예외를_반환한다() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}