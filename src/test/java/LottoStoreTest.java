package domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoStoreTest {
    private LottoStore lottoStore;

    @BeforeEach
    void setUP() {
        lottoStore = new LottoStore();
    }

    @Test
    void 수동_로또와_남은_금액만큼의_자동_로또를_합쳐서_구매한다() {
        Money money = new Money(5000); // 5000원
        ManualLottos manualLottos = new ManualLottos();

        // 수동 로또 2장 추가 (2000원 어치)
        Lotto manual1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto manual2 = createLotto(7, 8, 9, 10, 11, 12);
        manualLottos.add(manual1);
        manualLottos.add(manual2);

        LottoTickets ticket = lottoStore.buy(money, manualLottos);

        // 1. 전체 개수 검증: 5000원 = 5장이어야 함
        assertThat(ticket.lottos()).hasSize(5);

        // 2. 내용 검증: 수동으로 산 2장이 확실히 포함되어 있는지
        assertThat(ticket.lottos()).contains(manual1, manual2);
    }

    @Test
    void 수동_로또_없이_자동으로만_구매한다() {
        Money money = new Money(3000); // 3000원
        ManualLottos emptyManualLottos = new ManualLottos(); // 0장

        LottoTickets ticket = lottoStore.buy(money, emptyManualLottos);

        assertThat(ticket.lottos()).hasSize(3);
    }

    @Test
    void 가진_돈보다_수동_로또_구매_비용이_크면_예외가_발생한다() {
        Money money = new Money(1000); // 1000원
        ManualLottos manualLottos = new ManualLottos();

        // 2000원 어치 수동 로또 시도
        manualLottos.add(createLotto(1, 2, 3, 4, 5, 6));
        manualLottos.add(createLotto(1, 2, 3, 4, 5, 7));

        assertThatThrownBy(() -> lottoStore.buy(money, manualLottos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 테스트 헬퍼 메서드
    private Lotto createLotto(int... numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}