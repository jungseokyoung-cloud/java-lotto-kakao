package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<Lotto> lottos = new ArrayList<>();
    private static final List<LottoNumber> ALL_NUMBERS = IntStream.rangeClosed(1, 45)
            .mapToObj(LottoNumber::new)
            .toList();

    public List<Lotto> generateLottos(Money money) {
        int count = money.availableLottoCount();

        lottos.clear();
        for(int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }

        return lottos;
    }

    private Lotto generateLotto() {
        List<LottoNumber> numbers = new ArrayList<>(ALL_NUMBERS);

        Collections.shuffle(numbers);

        List<LottoNumber> result = new ArrayList<>(numbers.subList(0, 6));
        return new Lotto(result);
    }

    public List<Rank> match(WinningLotto winningLotto) {
        return lottos.stream()
                // 사용자가 구매한 각각의 로또(lotto)를 WinningLotto와 비교합니다.
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }
}
