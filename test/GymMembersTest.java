import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GymMembersTest {

    GymMembers gm = new GymMembers();

    @Test
    public void setSocSecNumAndNameTest() {
        String gmTest = "8910233981, Gemma Giraff";
        gm.setPersonalInfo(gmTest);

        assertTrue("8910233981".equals(gm.getSocialSecurityNumber()));
        assertTrue("Gemma Giraff".equals(gm.getName()));

    }

    @Test
    public void setLatestPaymentTest() {
        gm.setLatestPayment("2022-11-07");
        LocalDate expectedDate = LocalDate.of(2022, 11, 7);
        LocalDate unexpectedDate = LocalDate.of(2022, 11, 8);

        assertEquals(expectedDate, gm.getLatestPayment());
        assertNotEquals(unexpectedDate, gm.getLatestPayment());
    }

}
