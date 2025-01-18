package cleancode.studycafe.mine.studycafe.pass.item;

import cleancode.studycafe.mine.studycafe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;

import java.util.List;

public class StudyCafeLockerPassItems {

    private final List<StudyCafeLockerPass> items;

    private StudyCafeLockerPassItems(List<StudyCafeLockerPass> items) {
        this.items = items;
    }

    public static StudyCafeLockerPassItems of(List<String> lines) {
        List<StudyCafeLockerPass> items = lines.stream()
                .map(StudyCafeLockerPassItems::createItem)
                .toList();

        return new StudyCafeLockerPassItems(items);
    }

    private static StudyCafeLockerPass createItem(String line) {
        String[] values = line.split(",");

        StudyCafePassType passType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);

        return StudyCafeLockerPass.of(passType, duration, price);
    }

    public StudyCafeLockerPass selectableLockerPass(StudyCafePass pass) {
        if (pass.isNotFixedType()) {
            return null;
        }

        return items.stream()
                .filter(lockerPass -> lockerPass.isSelectable(pass))
                .findFirst()
                .orElse(null);
    }
}
