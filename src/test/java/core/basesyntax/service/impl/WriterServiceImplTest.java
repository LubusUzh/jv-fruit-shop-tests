package core.basesyntax.service.impl;

import static org.junit.Assert.assertEquals;

import core.basesyntax.service.WriterService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;

public class WriterServiceImplTest {
    private static final String PATH = "src/test/resources/test_report.csv";
    private WriterService writerService;

    @Before
    public void setUp() throws Exception {
        writerService = new WriterServiceImpl();
    }

    @Test
    public void writeToFile_OK() {
        String expected = null;
        String actual = "fruit,quantity";
        writerService.writeToFile(PATH, actual);
        try {
            expected = Files.readString(Path.of(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void nullPath_WriteToFile_NotOK() {
        writerService.writeToFile(null, "fruit,quantity");
    }

    @Test (expected = RuntimeException.class)
    public void wrongPath_WriteToFile_NotOK() {
        String wrongPath = "src/main/resources/smth/report.csv";
        writerService.writeToFile(wrongPath, "fruit,quantity");
    }

    @Test (expected = NullPointerException.class)
    public void nullReport_WriteToFile_NotOK() {
        writerService.writeToFile(PATH, null);
    }

    @Test (expected = NullPointerException.class)
    public void nullValues_WriteToFile_NotOK() {
        writerService.writeToFile(null, null);
    }
}
