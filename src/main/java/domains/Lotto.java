package domains;

import java.util.Arrays;

public class Lotto {
    private LottoNumber[] numbers;


    public Lotto(int... numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException("로또번호는 6개여야 합니다.");
        }

        try {
            this.numbers = Arrays.stream(numbers)
                    .mapToObj(LottoNumber::new)
                    .distinct()
                    .toArray(LottoNumber[]::new);
        } catch (Exception e) {
            throw e;
        }

        if (this.numbers.length != 6) {
            throw new IllegalArgumentException("로또번호는 중복되면 안 됩니다.");
        }
    }
}