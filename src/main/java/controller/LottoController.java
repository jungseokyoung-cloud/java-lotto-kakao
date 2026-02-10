package controller;

import domains.Lotto;
import domains.LottoNumber;
import domains.Money;
import view.InputView;

import java.util.List;

public class LottoController {
    public static void run() {
        Money userMoney = InputView.inputMoney();
        Lotto winningLotto = InputView.inputWinningNumbers();
        LottoNumber lottoNumber = InputView.inputBonusNumber();
    }
}
