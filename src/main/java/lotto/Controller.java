package lotto;

import java.util.Arrays;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.BonusNumber;
import lotto.model.number.Number;
import lotto.model.number.WinningNumbers;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

	public void run() {
		Money money = Money.from(InputView.askMoneyAmount());

		Lottos lottos = purchaseLottos(money);

		WinningNumbers winningNumbers = getWinningNumbers();
		PrizeInformations prizeInformations =
				getPrize(lottos, winningNumbers, getBonusNumber(winningNumbers));

		ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
	}

	private PrizeInformations getPrize(
			Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
		List<MatchResult> matchResults = lottos.match(winningNumbers, bonusNumber);

		return getPrizeInformations(matchResults);
	}

	private PrizeInformations getPrizeInformations(List<MatchResult> matchResults) {
		PrizeInformations prizeInformations = PrizeInformations.from(matchResults);
		ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));

		return prizeInformations;
	}

	private Lottos purchaseLottos(Money money) {
		int purchaseCount = getPurchaseCount(money);

		return purchaseLottos(purchaseCount);
	}

	private int getPurchaseCount(Money money) {
		int purchaseCount = Lotto.countAvailableTickets(money);
		ResultView.showPurchaseCount(purchaseCount);

		return purchaseCount;
	}

	private Lottos purchaseLottos(int purchaseCount) {
		Lottos lottos = Lottos.purchase(purchaseCount);
		ResultView.showLottos(LottoDTO.from(lottos));

		return lottos;
	}

	private WinningNumbers getWinningNumbers() {
		List<String> winningNumbersInput = Arrays.asList(InputView.askWinningNumbers());

		return WinningNumbers.from(winningNumbersInput);
	}

	private BonusNumber getBonusNumber(WinningNumbers winningNumbers) {
		String bonusNumberInput = InputView.askBonusNumber();

		return BonusNumber.from(Number.from(bonusNumberInput), winningNumbers);
	}

}
