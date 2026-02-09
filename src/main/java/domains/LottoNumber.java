package domains;

import java.util.Objects;

public class LottoNumber {
    private final int number;
    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
