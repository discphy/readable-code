package cleancode.studycafe.mine.studycafe.io;

import cleancode.studycafe.mine.studycafe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;
import cleancode.studycafe.mine.studycafe.pass.order.StudyCafePassOrder;

import static java.util.stream.IntStream.range;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    @Override
    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    @Override
    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    @Override
    public void showPassListForSelection(StudyCafePasses passes) {
        System.out.println();
        System.out.println("이용권 목록");

        range(0, passes.size())
                .forEach(index -> showPassForSelection(index, passes));
    }

    @Override
    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            lockerPass.display()
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    @Override
    public void showPassOrderSummary(StudyCafePassOrder order) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + order.passDisplay());
        if (order.containsLockerPass()) {
            System.out.println("사물함: " + order.lockerPassDisplay());
        }

        int discountPrice = order.discountPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = order.totalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    @Override
    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    private void showPassForSelection(int index, StudyCafePasses passes) {
        StudyCafePass pass = passes.findPassByIndex(index);
        System.out.println(String.format("%s. ", index + 1) + pass.display());
    }
}
