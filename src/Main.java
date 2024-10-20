import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Main {

    public Main() {

        CheckIfMember check = new CheckIfMember();
        Path toMembersList = Paths.get("src/data_inlamningsuppg2.txt");
        ArrayList<GymMembers> membersList = check.readMembersList(toMembersList);
        String checkString = check.compareCustomerInfo(false, "Not a test");
        System.out.println(check.printMembershipStatus(check.checkMemberStatus(checkString, membersList)));
    }

    public static void main(String[] args) {
        Main m = new Main();

    }
}