package controller;

import domains.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LottoController {
    public static void run() {
        Money userMoney = retry(() -> new Money(InputView.inputMoney()));

        LottoTickets lottoTickets = new LottoTickets();
        List<Lotto> lottos = lottoTickets.generateLottos(userMoney);

        OutputView.printLottos(lottos);

        Lotto winningLottoNumbers = retry(() -> {
            List<Integer> rawNumbers = InputView.inputWinningNumbers();
            List<LottoNumber> lottoNumbers = rawNumbers.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            return new Lotto(lottoNumbers);
        });

        WinningLotto winningLotto = retry(() -> {
            LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
            return new WinningLotto(winningLottoNumbers, bonusNumber);
        });

        List<Rank> ranks = lottoTickets.match(winningLotto);

        OutputView.printWinning(ranks);
        OutputView.printRateOfReturn(userMoney.calculateRate(ranks));
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
        }
    }
}
