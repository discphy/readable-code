package cleancode.studycafe.mine.studycafe.pass.order;

import cleancode.studycafe.mine.studycafe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;

public class StudyCafePassOrder {

    private final StudyCafePass pass;
    private final StudyCafeLockerPass lockerPass;

    private StudyCafePassOrder(StudyCafePass pass, StudyCafeLockerPass lockerPass) {
        this.pass = pass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafePass pass, StudyCafeLockerPass lockerPass) {
        return new StudyCafePassOrder(pass, lockerPass);
    }

    public boolean containsLockerPass() {
        return lockerPass != null;
    }

    public String passDisplay() {
        return pass.display();
    }

    public String lockerPassDisplay() {
        return lockerPass.display();
    }

    public int discountPrice() {
        return (int) (pass.getPrice() * pass.getDiscountRate());
    }

    public int totalPrice() {
        return pass.getPrice() - discountPrice() + lockerPassPrice();
    }

    private int lockerPassPrice() {
        if (lockerPass == null) {
            return 0;
        }

        return lockerPass.getPrice();
    }
}
