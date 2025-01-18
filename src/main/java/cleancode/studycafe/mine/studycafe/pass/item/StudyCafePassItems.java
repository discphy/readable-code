package cleancode.studycafe.mine.studycafe.pass.item;

import cleancode.studycafe.mine.studycafe.pass.StudyCafePass;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePassType;
import cleancode.studycafe.mine.studycafe.pass.StudyCafePasses;

import java.util.List;

public class StudyCafePassItems {

    private final List<StudyCafePass> items;

    private StudyCafePassItems(List<StudyCafePass> items) {
        this.items = items;
    }

    public static StudyCafePassItems of(List<String> lines) {
        List<StudyCafePass> items = lines.stream()
                .map(StudyCafePassItems::createItem)
                .toList();

        return new StudyCafePassItems(items);
    }

    private static StudyCafePass createItem(String line) {
        String[] values = line.split(",");

        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);
        double discountRate = Double.parseDouble(values[3]);

        return StudyCafePass.of(studyCafePassType, duration, price, discountRate);
    }

    public StudyCafePasses selectablePasses(StudyCafePassType passType) {
        List<StudyCafePass> selectablePasses = items.stream()
                .filter(pass -> pass.isEqualType(passType))
                .toList();
        return StudyCafePasses.of(selectablePasses);
    }
}
