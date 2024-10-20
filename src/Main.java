import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public Main() {

        CheckIfMember check = new CheckIfMember();
        Path toMembersList = Paths.get("src/data_inlamningsuppg2.txt");
        System.out.println(check.printMembershipStatus(check.checkMemberStatus(check.compareCustomerInfo(false, "Not a test"), check.readMembersList(toMembersList))));
    }

    public static void main(String[] args) {
        Main m = new Main();

    }
}