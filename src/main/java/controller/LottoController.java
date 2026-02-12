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
        ManualLottos manualLottos = new ManualLottos();
        LottoStore store = new LottoStore();

        int manualCount = retry(() -> {
           int count = InputView.inputManualCount();
           userMoney.validatePurchasable(count);

           return count;
        });

        OutputView.printManualLottoInputHeader();

        for(int i = 0; i < manualCount; i++) {
            retry(() -> {
                List<Integer> lotto = InputView.inputManualNumbers();

                manualLottos.add(lotto);
                return null;
            });
        }
        LottoTickets tickets = store.buy(userMoney, manualLottos);
        int autoCount = tickets.lottos().size() - manualCount;
        OutputView.printLottos(manualCount, autoCount, tickets.lottos());

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


        List<Rank> ranks = tickets.match(winningLotto);

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
