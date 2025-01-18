package cleancode.studycafe.mine.studycafe.config;

import cleancode.studycafe.mine.studycafe.io.FileHandler;
import cleancode.studycafe.mine.studycafe.io.InputHandler;
import cleancode.studycafe.mine.studycafe.io.OutputHandler;

public class CafeConfig {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    public CafeConfig(InputHandler inputHandler, OutputHandler outputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.fileHandler = fileHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
