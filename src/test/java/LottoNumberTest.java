import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
@Test
    public void 숫자가_1애서_45까지만_가능한지_검증한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber number = new LottoNumber(-1);
        });
    }
}
