package cleancode.studycafe.mine.studycafe.io;

import cleancode.studycafe.mine.studycafe.pass.item.StudyCafeLockerPassItems;
import cleancode.studycafe.mine.studycafe.pass.item.StudyCafePassItems;

public interface FileHandler {

    StudyCafePassItems readPasses();

    StudyCafeLockerPassItems readLockerPasses();
}
