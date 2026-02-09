import domains.Lotto;
import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    public void 번호가_6개가_아닌경우_에러를_반환한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = new Lotto(1, 2, 3, 4, 5);
        });
    }
}
