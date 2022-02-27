package lotto.model.prize;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.Money;

public class PrizeInformations {
	private final List<PrizeInformation> prizeInformations;

	public PrizeInformations(List<PrizeInformation> prizeInformations) {
		this.prizeInformations = prizeInformations;
	}

	public static PrizeInformations from(List<MatchResult> matchResults) {
		List<PrizeInformation> prizeInformations = Arrays.stream(Prize.values())
				.filter(prize -> prize != Prize.NONE)
				.map(prize -> PrizeInformation.of(matchResults, prize))
				.collect(Collectors.toList());

		return new PrizeInformations(prizeInformations);
	}

	public double calculateEarningRate(Money money) {
		return money.rate(getTotalAmount());
	}

	private int getTotalAmount() {
		return prizeInformations.stream()
				.mapToInt(PrizeInformation::pickAmount)
				.sum();
	}

	public List<PrizeInformation> getPrizeInformations() {
		return prizeInformations;
	}
}
