import domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketTest {
    private Money money;
    private LottoTickets lottoTickets;
    @BeforeEach
    public void setUP() {
        money = new Money(14000);
        lottoTickets = new LottoTickets();
    }


    @Test
    public void 발행된_여러_장의_로또_티켓은_서로_중복되지_않는다() {
        List<Lotto> lottos = lottoTickets.generateLottos(money);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        assertEquals(lottos.size(), new HashSet<>(lottos).size());
    }
}
