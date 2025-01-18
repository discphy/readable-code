package cleancode.studycafe.mine.studycafe.io.info;

import cleancode.studycafe.mine.studycafe.pass.info.StudyCafePassInfo;

public interface StudyCafeInfoProvidable {

    String provide(StudyCafePassInfo passInfo);

    boolean supports(StudyCafePassInfo passInfo);
}
