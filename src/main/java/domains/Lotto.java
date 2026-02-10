package domains;

import java.util.*;
import java.util.stream.Collectors;

public record Lotto(List<LottoNumber> numbers) {
    private static final Integer LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);

        List<LottoNumber> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    public Lotto(Integer... numbers) {
        this(toLottoNumberList(numbers));
    }

    private static List<LottoNumber> toLottoNumberList(Integer[] numbers) {
        return Arrays.stream(numbers)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return Objects.equals(numbers, number);
    }

    @Override
    public List<LottoNumber> numbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object o) {
        List<LottoNumber> thatNumbers = ((Lotto) o).numbers();
        for (int i = 0; i < 6; i++) {
            if (!thatNumbers.get(i).equals(this.numbers.get(i))) return false;
        }
        return true;
    }

    public Rank match(Lotto winningLotto, LottoNumber bonusNumber) {
        Integer matchCount = countMatches(winningLotto);
        boolean matchBonus = contains(bonusNumber);

        return Rank.valueOf(matchCount, matchBonus);
    }

    // 배열 스트림(Arrays.stream) -> 리스트 스트림(numbers.stream) 변경
    public Integer countMatches(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    // List의 toString 사용
    @Override
    public String toString() {
        return numbers.toString();
    }
}