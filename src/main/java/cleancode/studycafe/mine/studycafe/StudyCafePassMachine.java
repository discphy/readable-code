package cleancode.studycafe.mine.studycafe;

import cleancode.studycafe.mine.cafe.CafeInitializable;
import cleancode.studycafe.mine.cafe.CafeRunnable;
import cleancode.studycafe.mine.studycafe.config.CafeConfig;
import cleancode.studycafe.mine.studycafe.exception.AppException;
import cleancode.studycafe.mine.studycafe.io.FileHandler;
import cleancode.studycafe.mine.studycafe.io.InputHandler;
import cleancode.studycafe.mine.studycafe.io.OutputHandler;
import cleancode.studycafe.mine.studycafe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;
import cleancode.studycafe.mine.studycafe.pass.item.StudyCafeLockerPassItems;
import cleancode.studycafe.mine.studycafe.pass.item.StudyCafePassItems;
import cleancode.studycafe.mine.studycafe.pass.order.StudyCafePassOrder;

public class StudyCafePassMachine implements CafeInitializable, CafeRunnable {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    private StudyCafePassItems studyCafePassesItems;
    private StudyCafeLockerPassItems lockerPassesItems;

    public StudyCafePassMachine(CafeConfig config) {
        this.inputHandler = config.getInputHandler();
        this.outputHandler = config.getOutputHandler();
        this.fileHandler = config.getFileHandler();
    }

    @Override
    public void initialize() {
        studyCafePassesItems = fileHandler.readPasses();
        lockerPassesItems = fileHandler.readLockerPasses();
    }

    @Override
    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePassType selectedPassType = selectedPassType();
            StudyCafePass selectedPass = selectedPass(selectedPassType);
            StudyCafeLockerPass selectedLockerPass = selectedLockerPass(selectedPass);

            StudyCafePassOrder order = StudyCafePassOrder.of(selectedPass, selectedLockerPass);
            outputHandler.showPassOrderSummary(order);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassType selectedPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass selectedPass(StudyCafePassType passType) {
        StudyCafePasses selectablePasses = studyCafePassesItems.selectablePasses(passType);

        outputHandler.showPassListForSelection(selectablePasses);
        return inputHandler.getSelectPass(selectablePasses);
    }

    private StudyCafeLockerPass selectedLockerPass(StudyCafePass selectedPass) {
        StudyCafeLockerPass selectableLockerPass = lockerPassesItems.selectableLockerPass(selectedPass);

        if (selectableLockerPass == null) {
            return null;
        }

        outputHandler.askLockerPass(selectableLockerPass);
        return inputHandler.getLockerSelection() ? selectableLockerPass : null;
    }
}
