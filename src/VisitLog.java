import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitLog {

    boolean isTest = false;
    Path toLog;

    public void writeVisitToFile(GymMembers gm, boolean isTest) {
        if (isTest) {
            toLog = Paths.get("test/testVi" +
                    "sitLog.txt");
        } else {
            toLog = Paths.get("src/visitlog.txt");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(toLog), true))) {
            if (!Files.exists(toLog)) {
                Files.createFile(toLog);
            }
            bw.write(formatVisitLog(gm));

        } catch (IOException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String formatVisitLog(GymMembers gm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return gm.getSocialSecurityNumber() + ", " + gm.getName() + "\n" +
                formatter.format(LocalDateTime.now()) + "\n";
    }

}
