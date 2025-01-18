package cleancode.studycafe.mine.studycafe.pass.info;

import cleancode.studycafe.mine.studycafe.io.info.StudyCafeInfoProvider;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;

public class StudyCafePassInfo {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafePassInfo(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafePassInfo of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafePassInfo(passType, duration, price);
    }

    public boolean isEqualType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isEqualDuration(int duration) {
        return this.duration == duration;
    }

    public boolean isLockerSelectable(StudyCafePassInfo info) {
        return info.isEqualType(passType) && info.isEqualDuration(duration);
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public String display() {
        return StudyCafeInfoProvider.display(this);
    }
}
