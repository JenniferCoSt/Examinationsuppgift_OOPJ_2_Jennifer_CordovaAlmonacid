import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class VisitLogTest {

    VisitLog vl = new VisitLog();

    GymMembers gm1 = new GymMembers("7703021234", "Alhambra Aromes", "2024-07-01");
    GymMembers gm2 = new GymMembers("8707151234", "Gemma Giraff", "2022-06-22");

    @Test
    public void writeVisitToFileTest() throws IOException {
        Path pathTest = Paths.get("test/testVisitLog.txt");
        boolean isTest = true;
        vl.writeVisitToFile(gm1, isTest);
        vl.writeVisitToFile(gm2, isTest);

        String readFromFile = Files.readString(pathTest);

        assertTrue(readFromFile.contains("Alhambra Aromes"));
        assertTrue(readFromFile.contains("Gemma Giraff"));
        assertFalse(readFromFile.contains("Alhambra AromesX"));
    }

    @Test
    public void logVisitTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        assertEquals("7703021234, Alhambra Aromes\n" + dtf.format(LocalDateTime.now()) + "\n", vl.formatVisitLog(gm1));
        assertNotEquals("7703021234, Alhambra AromesX\n" + dtf.format(LocalDateTime.now()) + "\n", vl.formatVisitLog(gm1));
    }


}
