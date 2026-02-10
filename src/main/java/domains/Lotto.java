package domains;

import java.util.Arrays;
import java.util.List;

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
        this.numbers = numbers.stream()
                .toArray(LottoNumber[]::new);
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

    public Rank match(Lotto winningLotto, LottoNumber bonusNumber) {
        int matchCount = countMatches(winningLotto);
        boolean matchBonus = contains(bonusNumber);

        return Rank.valueOf(matchCount, matchBonus);
    }

    // 배열을 스트림으로 변환하여 매칭 개수 계산
    public int countMatches(Lotto winningLotto) {
        return (int) Arrays.stream(numbers)
                .filter(winningLotto::contains)
                .count();
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}