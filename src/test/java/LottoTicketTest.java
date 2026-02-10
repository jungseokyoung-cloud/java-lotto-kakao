import domains.Lotto;
import domains.LottoTickets;
import domains.Money;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketTest {
    @Test
    public void 로또_번호들이_중복되지_않는지_검증한다() {
        Money money = new Money(14000);
        LottoTickets lottoTickets = new LottoTickets();

        List<Lotto> lottos = lottoTickets.generateLottos(money);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        assertEquals(lottos.size(), new HashSet<>(lottos).size());
    }
}
