package domains;

import java.util.ArrayList;
import java.util.List;

public class ManualLottos {
    private final List<Lotto> lottos;

    public ManualLottos() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        if (lottos.contains(lotto)) {
            throw new IllegalArgumentException("이미 입력된 로또 번호입니다.");
        }
        lottos.add(lotto);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos); // 방어적 복사
    }
}