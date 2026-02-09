import domains.Lotto;
import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    @Test
    public void 번호가_6개가_아닌경우_에러를_반환한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(1, 2, 3, 4, 5);
        });
    }

    @Test
    public void 번호가_중복되지_않는지_검증한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(1, 2, 2, 4, 5, 6);
        });
    }

    @Test
    public void 번호가_포함되어_있는지_확인할_수_있다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoNumber number = new LottoNumber(2);

        assertTrue(lotto.contains(number));
    }

    @Test
    public void 로또는_오름차순이어야_한다() {
        Lotto lotto1 = new Lotto(6, 5, 4, 3, 2, 1);
        Lotto lotto2 = new Lotto(1, 2, 3, 4, 5, 6);

        assertEquals(lotto1, lotto2);
    }
}
