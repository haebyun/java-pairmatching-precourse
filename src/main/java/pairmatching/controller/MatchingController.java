package pairmatching.controller;

import java.util.List;
import pairmatching.model.service.PairMatchingService;
import pairmatching.model.validation.InputValidator;
import pairmatching.util.Separator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final PairMatchingService matchingService;
    private static final String FEATURE_OPTION_MATCHING = "1";
    private static final String FEATURE_OPTION_SEARCHING = "2";
    private static final String FEATURE_OPTION_RESET = "3";
    private static final String FEATURE_OPTION_QUIT = "Q";

    public MatchingController(PairMatchingService matchingService) {
        this.matchingService = matchingService;
    }

    public void run() {
        while (true) {
            String input = InputView.getFeatureOption();
            if (input.equals(FEATURE_OPTION_QUIT)) {
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
        if (input.equals(FEATURE_OPTION_MATCHING)) {
            OutputView.printSequenceInformations();
            processMatching();
            return;
        }
        if (input.equals(FEATURE_OPTION_SEARCHING)) {
            OutputView.printSequenceInformations();
            processSearching();
            return;
        }
        if (input.equals(FEATURE_OPTION_RESET)) {
            processReset();
            OutputView.printResetCompleteMessage();
        }
    }

    private void processMatching() {
        String sequenceOfOptions = InputView.getSequenceOfOptions();
        if (matchingService.hasResult(sequenceOfOptions)) {
            if (!shouldReMatch()) {
                System.out.println();
                processMatching();
                return;
            }
        }
        startMatch(sequenceOfOptions);
    }

    private boolean shouldReMatch() {
        String option = InputView.getRematchingOption();
        InputValidator.validateRematchingOption(option);
        return wantReMatching(option);
    }

    private void startMatch(String sequenceOfOptions) {
        List<String> options = Separator.separateByComma(sequenceOfOptions);
        InputValidator.validateSequenceOfOptions(options);

        matchingService.makeMatching(sequenceOfOptions, options);
        OutputView.printMatchingResults(matchingService.getMatchingResult(sequenceOfOptions));
    }

    private void processSearching() {
        String sequenceOfOptions = InputView.getSequenceOfOptions();
        if (!matchingService.hasResult(sequenceOfOptions)) {
            OutputView.printNoMatchingMessage();
            return;
        }
        OutputView.printMatchingResults(matchingService.getMatchingResult(sequenceOfOptions));
    }

    private void processReset() {
        matchingService.resetMatchingResults();
        OutputView.printResetCompleteMessage();
    }

    private boolean wantReMatching(String option) {
        return option.equals("네");
    }
}
