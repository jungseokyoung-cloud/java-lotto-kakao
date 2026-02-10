package controller;

import domains.Money;
import view.InputView;

public class LottoController {
    public static void run() {
        Money userMoney = new Money(InputView.inputMoney());

    }
}
