import domains.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {
    @Test
    public void 로또_번호가_1에서_45_사이가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber number = new LottoNumber(-1);
        });
    }

    @Test
    public void 로또_번호의_값이_같으면_동일한_객체로_판단한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);
        assertEquals(lottoNumber2, lottoNumber1);
    }

    @Test
    public void 로또_번호의_값이_다르면_다른_객체로_판단한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(2);
        assertNotEquals(lottoNumber2, lottoNumber1);
    }
}
