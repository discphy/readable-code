package cleancode.studycafe.mine;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.io.ConsoleInputHandler;
import cleancode.studycafe.mine.io.ConsoleOutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;
import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafePass;
import cleancode.studycafe.mine.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();

    private final List<StudyCafePass> studyCafePasses;
    private final List<StudyCafeLockerPass> lockerPasses;

    public StudyCafePassMachine() {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void run() {
        try {
            consoleOutputHandler.showWelcomeMessage();
            consoleOutputHandler.showAnnouncement();

            StudyCafePassType studyCafePassType = selectedPassType();
            List<StudyCafePass> passes = filterPassesBy(studyCafePassType);
            StudyCafePass selectedPass = selectedPass(passes);
            boolean lockerSelection = false;

            StudyCafeLockerPass lockerPass = findLockerPassBy(selectedPass);

            if (lockerPass != null) {
                consoleOutputHandler.askLockerPass(lockerPass);
                lockerSelection = consoleInputHandler.getLockerSelection();
            }

            if (lockerSelection) {
                consoleOutputHandler.showPassOrderSummary(selectedPass, lockerPass);
            } else {
                consoleOutputHandler.showPassOrderSummary(selectedPass, null);
            }
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private List<StudyCafePass> filterPassesBy(StudyCafePassType passType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == passType)
                .toList();
    }

    private StudyCafePassType selectedPassType() {
        consoleOutputHandler.askPassTypeSelection();
        return consoleInputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass selectedPass(List<StudyCafePass> passes) {
        consoleOutputHandler.showPassListForSelection(passes);
        return consoleInputHandler.getSelectPass(passes);
    }

    private StudyCafeLockerPass findLockerPassBy(StudyCafePass selectedPass) {
        if (selectedPass.getPassType() != StudyCafePassType.FIXED) {
            return null;
        }

        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }

}
