package cleancode.studycafe.mine.studycafe.io;

import cleancode.studycafe.mine.studycafe.pass.item.StudyCafeLockerPassItems;
import cleancode.studycafe.mine.studycafe.pass.item.StudyCafePassItems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudyCafeFileHandler implements FileHandler {

    public static final String ITEMS_FILE_DIRECTORY_PATH = "src/main/resources/cleancode/studycafe/";
    public static final String PASS_ITEMS_FILE_NAME = "pass-list";
    public static final String LOCKER_PASS_ITEMS_FILE_NAME = "locker";

    @Override
    public StudyCafePassItems readPasses() {
        List<String> lines = readPassItemLines(PASS_ITEMS_FILE_NAME);
        return StudyCafePassItems.of(lines);
    }

    @Override
    public StudyCafeLockerPassItems readLockerPasses() {
        List<String> lines = readPassItemLines(LOCKER_PASS_ITEMS_FILE_NAME);
        return StudyCafeLockerPassItems.of(lines);
    }

    private List<String> readPassItemLines(String fileName) {
        try {
            Path path = getFilePath(fileName);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private Path getFilePath(String fileName) {
        return Paths.get(ITEMS_FILE_DIRECTORY_PATH + fileName + ".csv");
    }
}
