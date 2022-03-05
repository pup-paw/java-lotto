package lotto;

import lotto.model.Money;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningBalls;
import lotto.model.prize.PrizeInformation;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

	public void run() {
		Money money = getMoney();
		int totalCount = getTotalCount(money);
		int manualCount = getManualCount(totalCount);
		Lottos lottos = purchase(totalCount, manualCount);

		showPurchase(totalCount, manualCount, lottos);

		WinningBalls winningBalls = getWinningNumbers();
		PrizeInformation prizeInformation =
			getPrize(lottos, winningBalls);

		ResultView.showEarningRate(prizeInformation.calculateEarningRate(money));
	}

	private Money getMoney() {
		try {
			return Money.from(InputView.askMoneyAmount());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getMoney();
		}
	}

	private int getTotalCount(Money money) {
		try {
			return Lotto.countTickets(money);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getTotalCount(money);
		}
	}

	private int getManualCount(int totalCount) {
		try {
			return InputView.askManualLottoCount(totalCount);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getManualCount(totalCount);
		}
	}

	private Lottos purchase(int totalCount, int manualCount) {
		try {
			return Lottos.purchase(totalCount, manualCount, InputView.askManualLottoNumbers(manualCount));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return purchase(totalCount, manualCount);
		}
	}

	private void showPurchase(int totalCount, int manualCount, Lottos lottos) {
		ResultView.showPurchaseCount(totalCount, manualCount);
		ResultView.showLottos(LottoDTO.from(lottos));
	}

	private PrizeInformation getPrize(Lottos lottos, WinningBalls winningBalls) {
		PrizeInformation prizeInformation = PrizeInformation.from(lottos, winningBalls);
		ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformation));

		return prizeInformation;
	}

	private WinningBalls getWinningNumbers() {
		try {
			return WinningBalls.from(InputView.askWinningNumbers(), InputView.askBonusNumber());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getWinningNumbers();
		}
	}

}
