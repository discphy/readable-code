package cleancode.studycafe.mine.studycafe.pass;

import cleancode.studycafe.mine.studycafe.pass.info.StudyCafePassInfo;

public class StudyCafeLockerPass {

    private final StudyCafePassInfo info;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        info = StudyCafePassInfo.of(passType, duration, price);
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    public boolean isSelectable(StudyCafePass pass) {
        return info.isLockerSelectable(pass.getInfo());
    }

    public int getPrice() {
        return info.getPrice();
    }

    public String display() {
        return info.display();
    }
}
