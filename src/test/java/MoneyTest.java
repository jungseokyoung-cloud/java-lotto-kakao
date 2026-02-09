import domains.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {
    @Test
    public void 돈이_들어오면_로또를_몇장_살_수_있는지_반환한다() {
        Money money = new Money(14243);

        assertEquals(14, money.availableLottoCount());
    }
}
