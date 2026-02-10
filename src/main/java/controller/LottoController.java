package controller;

import domains.Lotto;
import domains.LottoNumber;
import domains.LottoTickets;
import domains.Money;
import view.InputView;
import view.OutputView;

import java.util.function.Supplier;

public class LottoController {
    public static void run() {
        Money userMoney = retry(InputView::inputMoney);
        LottoTickets lottoTickets = new LottoTickets();
        OutputView.printLottos(lottoTickets.generateLottos(userMoney));

        Lotto winningLotto = retry(InputView::inputWinningNumbers);
        LottoNumber lottoNumber = retry(InputView::inputBonusNumber);
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
