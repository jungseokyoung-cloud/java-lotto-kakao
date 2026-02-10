package controller;

import domains.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    public static void run() {
        Money userMoney = retry(InputView::inputMoney);
        LottoTickets lottoTickets = new LottoTickets();
        List<Lotto> lottos = lottoTickets.generateLottos(userMoney);

        OutputView.printLottos(lottos);
        Lotto winningLotto = retry(InputView::inputWinningNumbers);
        LottoNumber bonusNumber = retry(InputView::inputBonusNumber);

        List<Rank> ranks = lottoTickets.match(winningLotto, bonusNumber);
        System.out.println(ranks.size());
        OutputView.printWinning(ranks);

        System.out.printf("총 수익률은 %f입니다.", userMoney.calculateRate(ranks));
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
