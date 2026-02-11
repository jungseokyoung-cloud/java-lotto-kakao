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

    @Test
    public void 당첨_번호와_3개가_일치하면_5등으로_판단한다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 9, 10, 11);

        assertEquals(Rank.FIFTH, lotto.match(winningLotto, new LottoNumber(44)));
    }
}
