package domains;

public class LottoNumber {
    private final int number;
    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
        this.number = number;
    }
}
