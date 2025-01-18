package cleancode.studycafe.mine.studycafe.io.info;

import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.info.StudyCafePassInfo;

import java.util.Arrays;

public enum StudyCafeInfoProvider implements StudyCafeInfoProvidable {

    HOURLY(StudyCafePassType.HOURLY) {
        @Override
        public String provide(StudyCafePassInfo passInfo) {
            return String.format("%s시간권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    WEEKLY(StudyCafePassType.WEEKLY) {
        @Override
        public String provide(StudyCafePassInfo passInfo) {
            return String.format("%s주권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    FIXED(StudyCafePassType.FIXED) {
        @Override
        public String provide(StudyCafePassInfo passInfo) {
            return String.format("%s주권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    ;

    private final StudyCafePassType passType;

    StudyCafeInfoProvider(StudyCafePassType passType) {
        this.passType = passType;
    }

    public static String display(StudyCafePassInfo passInfo) {
        StudyCafeInfoProvider provider = findBy(passInfo);
        return provider.provide(passInfo);
    }

    private static StudyCafeInfoProvider findBy(StudyCafePassInfo passInfo) {
        return Arrays.stream(values())
                .filter(provider -> provider.supports(passInfo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 이용권 정보입니다."));
    }

    @Override
    public boolean supports(StudyCafePassInfo passInfo) {
        return passInfo.isEqualType(passType);
    }
}
