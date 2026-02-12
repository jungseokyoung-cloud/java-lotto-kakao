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

        int manualCount = retry(() -> {
           int count = InputView.inputManualCount();
           userMoney.validatePurchasable(count);

           return count;
        });

        for(int i = 0; i < manualCount; i++) {
            retry(() -> {
                List<Integer> lotto = InputView.inputManualNumbers();

                manualLottos.add(lotto);
                return null;
            });
        }
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
