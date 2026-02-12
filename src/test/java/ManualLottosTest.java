import domains.Lotto;
import domains.ManualLottos;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualLottosTest {
    @Test
    void 중복되지_않은_로또는_정상적으로_추가된다() {
        ManualLottos manualLottos = new ManualLottos();
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(7, 8, 9, 10, 11, 12);

        manualLottos.add(lotto1);
        manualLottos.add(lotto2);

        assertThat(manualLottos.size()).isEqualTo(2);
        assertThat(manualLottos.getLottos()).containsExactly(lotto1, lotto2);
    }

    @Test
    void 이미_존재하는_로또를_추가하면_예외가_발생한다() {
        ManualLottos manualLottos = new ManualLottos();
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);
        manualLottos.add(lotto);

        Lotto duplicateLotto = createLotto(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> manualLottos.add(duplicateLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(numbers);
    }
}