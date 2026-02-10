package domains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                    .sorted()
                    .toArray(LottoNumber[]::new);
        } catch (Exception e) {
            throw e;
        }

        if (this.numbers.length != 6) {
            throw new IllegalArgumentException("로또번호는 중복되면 안 됩니다.");
        }
    }

    public Lotto(List<LottoNumber> numbers) {
        int[] lottoNumbers = numbers.stream()
                .mapToInt(LottoNumber::getNumber) // LottoNumber 객체에서 int 값 추출 (getter 이름에 맞게 수정하세요)
                .toArray();

        new Lotto(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return Arrays.asList(numbers).contains(number);
    }

    public LottoNumber[] getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        LottoNumber[] thatNumbers = ((Lotto) o).getNumbers();
        for (int i = 0; i < 6; i++) {
            if (!thatNumbers[i].equals(this.numbers[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }
}