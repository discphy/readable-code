package cleancode.studycafe.mine.studycafe.pass;

import cleancode.studycafe.mine.studycafe.pass.info.StudyCafePassInfo;

public class StudyCafePass {

    private final StudyCafePassInfo info;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.info = StudyCafePassInfo.of(passType, duration, price);
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public boolean isNotFixedType() {
        return !info.isEqualType(StudyCafePassType.FIXED);
    }

    public boolean isEqualType(StudyCafePassType passType) {
        return info.isEqualType(passType);
    }

    public StudyCafePassInfo getInfo() {
        return info;
    }

    public int getPrice() {
        return info.getPrice();
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        return info.display();
    }
}
