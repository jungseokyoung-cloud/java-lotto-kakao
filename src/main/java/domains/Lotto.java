package domains;

import java.lang.reflect.Array;
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

    public Rank match(Lotto winningLotto) {
        int matchCount = 0;

        // 상대방(당첨) 로또의 번호 배열을 가져옴
        LottoNumber[] winningNumbers = winningLotto.getNumbers();

        // 1. 내 번호 하나씩 꺼내기
        for (LottoNumber myNumber : this.numbers) {

            // 2. 당첨 번호들 중에서 내 번호와 같은 게 있는지 찾기
            for (LottoNumber targetNumber : winningNumbers) {
                if (myNumber.equals(targetNumber)) {
                    matchCount++;
                    break; // 하나 찾았으면 더 볼 필요 없이 다음 내 번호로 넘어감
                }
            }
        }

        // 3. Rank Enum에게 개수를 주고 등수를 받아옴 (보너스 볼은 일단 false)
        return Rank.valueOf(matchCount, false);
    }
}