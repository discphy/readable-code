package cleancode.studycafe.mine.studycafe.io;

import cleancode.studycafe.mine.studycafe.exception.AppException;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public StudyCafePass getSelectPass(StudyCafePasses passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.findPassByIndex(selectedIndex);
    }

    @Override
    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
