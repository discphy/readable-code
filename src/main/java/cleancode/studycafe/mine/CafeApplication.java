package cleancode.studycafe.mine;

import cleancode.studycafe.mine.studycafe.StudyCafePassMachine;
import cleancode.studycafe.mine.studycafe.config.CafeConfig;
import cleancode.studycafe.mine.studycafe.io.ConsoleInputHandler;
import cleancode.studycafe.mine.studycafe.io.ConsoleOutputHandler;
import cleancode.studycafe.mine.studycafe.io.StudyCafeFileHandler;

public class CafeApplication {

    public static void main(String[] args) {
        CafeConfig cafeConfig = new CafeConfig(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new StudyCafeFileHandler()
        );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(cafeConfig);
        studyCafePassMachine.initialize();
        studyCafePassMachine.run();
    }

}
