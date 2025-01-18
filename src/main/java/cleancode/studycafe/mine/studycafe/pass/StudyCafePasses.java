package cleancode.studycafe.mine.studycafe.pass;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public int size() {
        return passes.size();
    }

    public StudyCafePass findPassByIndex(int index) {
        return copyPasses().get(index);
    }

    private List<StudyCafePass> copyPasses() {
        return List.copyOf(passes);
    }
}
