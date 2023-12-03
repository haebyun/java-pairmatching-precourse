package pairmatching.controller;

import java.util.List;
import pairmatching.model.service.PairMatchingService;
import pairmatching.model.validation.InputValidator;
import pairmatching.util.Separator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final PairMatchingService matchingService;

    public MatchingController(PairMatchingService matchingService) {
        this.matchingService = matchingService;
    }

    public void run() {
        while (true) {
            String input = InputView.getFeatureOption();
            if (input.equals("Q")) {
                break;
            }

            try {
                InputValidator.validateFeatureOption(input);
                deliverInputToService(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void deliverInputToService(String input) {
        if (input.equals("1")) {
            OutputView.printSequenceInformations();
            deliverInputForMatching();
        }
        if (input.equals("2")) {
            OutputView.printSequenceInformations();
            deliverInputForSearching();
        }
        if (input.equals("3")) {
            matchingService.resetMatchingResults();
            OutputView.printResetCompleteMessage();
        }
    }

    private void deliverInputForMatching() {
        String sequenceOfOptions = InputView.getSequenceOfOptions();
        if (matchingService.hasResult(sequenceOfOptions)) {
            String option = InputView.getRematchingOption();
            InputValidator.validateRematchingOption(option);
            if (!wantReMatching(option)) {
                System.out.println();
                deliverInputForMatching();
                return;
            }
        }
        List<String> options = Separator.separateByComma(sequenceOfOptions);
        InputValidator.validateSequenceOfOptions(options);
        matchingService.makeMatching(sequenceOfOptions, options);
        OutputView.printMatchingResults(matchingService.getMatchingResult(sequenceOfOptions));
    }

    private boolean wantReMatching(String option) {
        return option.equals("네");
    }

    private void deliverInputForSearching() {
        String sequenceOfOptions = InputView.getSequenceOfOptions();
        if (!matchingService.hasResult(sequenceOfOptions)) {
            OutputView.printNoMatchingMessage();
            return;
        }
        OutputView.printMatchingResults(matchingService.getMatchingResult(sequenceOfOptions));
    }
}
