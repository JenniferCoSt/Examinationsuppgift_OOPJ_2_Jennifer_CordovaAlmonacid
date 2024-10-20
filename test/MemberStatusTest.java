import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberStatusTest {

    @Test
    public void testMemberStatus() {
        assertEquals("medlem", MemberStatus.MEMBER.status);
        assertEquals("tidigare medlem", MemberStatus.FORMER_MEMBER.status);
        assertEquals("inte medlem", MemberStatus.NOT_MEMBER.status);
        assertNotEquals("medlem", MemberStatus.FORMER_MEMBER.status);
    }
}