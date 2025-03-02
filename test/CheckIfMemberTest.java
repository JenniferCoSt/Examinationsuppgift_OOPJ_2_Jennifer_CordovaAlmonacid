import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckIfMemberTest {

    CheckIfMember cim = new CheckIfMember();

    GymMembers expected1 = new GymMembers("7703021234", "Alhambra Aromes", "2024-07-01");
    GymMembers expected2 = new GymMembers("8204021234", "Bear Belle", "2019-12-02");
    GymMembers expected3 = new GymMembers("8512021234", "Chamade Coriola", "2018-03-12");
    GymMembers expected4 = new GymMembers("7608021234", "Diamanda Djedi", "2024-01-30");
    GymMembers expected5 = new GymMembers("7605021234", "Elmer Ekorrsson", "2022-11-07");

    List<GymMembers> listOfExpectedMembersTest = Arrays.asList(expected1, expected2, expected3, expected4, expected5);

    Path p = Paths.get("test/testdata_inlamningsuppg2.txt");
    ArrayList<GymMembers> testMembers = cim.readMembersList(p);

    @Test
    public void readMembersListTest() throws IOException {
        assertEquals(5, testMembers.size());
        assertEquals(listOfExpectedMembersTest.get(0).getName(), testMembers.get(0).getName());
        assertEquals(listOfExpectedMembersTest.get(2).getSocialSecurityNumber(), testMembers.get(2).getSocialSecurityNumber());
        assertNotEquals(listOfExpectedMembersTest.get(1).getLatestPayment(), testMembers.get(3).getLatestPayment());
    }

    @Test
    public void checkIfMemberTest() {
        assertEquals(cim.compareCustomerInfo(true, "Det här är ett test"), "Det här är ett test");
        assertEquals(cim.compareCustomerInfo(true, "Test igen"), "Test igen");
        assertNotEquals(cim.compareCustomerInfo(true, "Test igenX"), "Test igen");


    }

    @Test
    public void isMembershipValidTest() {
        testMembers.get(3).setLatestPayment(String.valueOf(LocalDate.now().minusWeeks(65)));

        assertTrue(cim.isMembershipValid(testMembers.getFirst()));
        assertFalse(cim.isMembershipValid(testMembers.get(3)));
        assertFalse(cim.isMembershipValid(testMembers.get(2)));
    }

    @Test
    public void checkMemberStatusTest() {
        assertEquals(cim.checkMemberStatus("Diamanda Djedi", testMembers), MemberStatus.MEMBER);
        assertEquals(cim.checkMemberStatus("8204021234", testMembers), MemberStatus.FORMER_MEMBER);
        assertTrue(cim.checkMemberStatus("Gemma Giraff", testMembers).equals(MemberStatus.NOT_MEMBER));
        assertFalse(cim.checkMemberStatus("9302114567", testMembers).equals(MemberStatus.MEMBER));
    }

    @Test
    public void printMembershipStatusTest() {
        assertTrue(cim.printMembershipStatus(MemberStatus.MEMBER).equals("Personen är medlem."));
        assertTrue(cim.printMembershipStatus(MemberStatus.FORMER_MEMBER).equals("Personen är tidigare medlem."));
        assertTrue(cim.printMembershipStatus(MemberStatus.NOT_MEMBER).equals("Personen är inte medlem."));
        assertFalse(cim.printMembershipStatus(MemberStatus.MEMBER).equals("Personen är inte medlem."));
    }
}
