package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.pass.StudyCafePass;
import cleancode.studycafe.mine.pass.StudyCafePassType;
import cleancode.studycafe.mine.pass.StudyCafePasses;

import java.util.Scanner;

public class ConsoleInputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

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

    public StudyCafePass getSelectPass(StudyCafePasses passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.findPassByIndex(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
