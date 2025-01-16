package cleancode.studycafe.mine;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.io.ConsoleInputHandler;
import cleancode.studycafe.mine.io.ConsoleOutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;
import cleancode.studycafe.mine.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.pass.StudyCafePass;
import cleancode.studycafe.mine.pass.StudyCafePassType;
import cleancode.studycafe.mine.pass.StudyCafePasses;

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
            StudyCafePasses passes = filterPassesBy(studyCafePassType);
            StudyCafePass selectedPass = selectedPass(passes);

            StudyCafeLockerPass lockerPass = selectedLockerPassBy(selectedPass);
            consoleOutputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePasses filterPassesBy(StudyCafePassType passType) {
        List<StudyCafePass> filteredPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isEqualType(passType))
                .toList();
        return StudyCafePasses.of(filteredPasses);
    }

    private StudyCafePassType selectedPassType() {
        consoleOutputHandler.askPassTypeSelection();
        return consoleInputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass selectedPass(StudyCafePasses passes) {
        consoleOutputHandler.showPassListForSelection(passes);
        return consoleInputHandler.getSelectPass(passes);
    }

    private StudyCafeLockerPass selectedLockerPassBy(StudyCafePass selectedPass) {
        StudyCafeLockerPass lockerPass = findLockerPassBy(selectedPass);

        if (doesLockerSelection(lockerPass)) {
            return lockerPass;
        }

        return null;
    }

    private StudyCafeLockerPass findLockerPassBy(StudyCafePass selectedPass) {
        if (selectedPass.isEqualType(StudyCafePassType.FIXED)) {
            return null;
        }

        return lockerPasses.stream()
                .filter(option -> option.isSelectable(selectedPass))
                .findFirst()
                .orElse(null);
    }

    private boolean doesLockerSelection(StudyCafeLockerPass lockerPass) {
        if (lockerPass == null) {
            return false;
        }

        consoleOutputHandler.askLockerPass(lockerPass);
        return consoleInputHandler.getLockerSelection();
    }

}
