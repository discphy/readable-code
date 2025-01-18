package cleancode.studycafe.mine.studycafe.io;

import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;

public interface InputHandler {


    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(StudyCafePasses passes);

    boolean getLockerSelection();
}
