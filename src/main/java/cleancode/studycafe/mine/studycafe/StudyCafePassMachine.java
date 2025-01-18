package cleancode.studycafe.mine.studycafe;

import cleancode.studycafe.mine.cafe.CafeInitializable;
import cleancode.studycafe.mine.cafe.CafeRunnable;
import cleancode.studycafe.mine.studycafe.config.CafeConfig;
import cleancode.studycafe.mine.studycafe.exception.AppException;
import cleancode.studycafe.mine.studycafe.io.*;
import cleancode.studycafe.mine.studycafe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;

import java.util.List;

public class StudyCafePassMachine implements CafeInitializable, CafeRunnable {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    private List<StudyCafePass> studyCafePasses;
    private List<StudyCafeLockerPass> lockerPasses;

    public StudyCafePassMachine(CafeConfig config) {
        this.inputHandler = config.getInputHandler();
        this.outputHandler = config.getOutputHandler();
        this.fileHandler = config.getFileHandler();
    }

    @Override
    public void initialize() {
        studyCafePasses = fileHandler.readStudyCafePasses();
        lockerPasses = fileHandler.readLockerPasses();
    }

    @Override
    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePassType studyCafePassType = selectedPassType();
            StudyCafePasses passes = filterPassesBy(studyCafePassType);
            StudyCafePass selectedPass = selectedPass(passes);

            StudyCafeLockerPass lockerPass = selectedLockerPassBy(selectedPass);
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePasses filterPassesBy(StudyCafePassType passType) {
        List<StudyCafePass> filteredPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isEqualType(passType))
                .toList();
        return StudyCafePasses.of(filteredPasses);
    }

    private StudyCafePassType selectedPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass selectedPass(StudyCafePasses passes) {
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
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

        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }
}
