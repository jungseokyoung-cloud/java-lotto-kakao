package controller;

import domains.Lotto;
import domains.LottoNumber;
import domains.Money;
import view.InputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {
    public static void run() {
        Money userMoney = retry(InputView::inputMoney);
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
