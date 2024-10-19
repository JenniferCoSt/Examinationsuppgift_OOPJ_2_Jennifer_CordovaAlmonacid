import java.time.LocalDate;
import java.time.LocalDateTime;

public class GymMembers {

    private String socialSecurityNumber;
    private String name;
    private LocalDate latestPayment;

    public GymMembers() {
    }

    public GymMembers(String socialSecurityNumber, String name, String latestPayment) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.latestPayment = LocalDate.parse(latestPayment);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLatestPayment() {
        return latestPayment;
    }

    protected void setPersonalInfo(String s) {
        this.socialSecurityNumber = s.substring(0, s.indexOf(',')).trim();
        this.name = s.substring(s.indexOf(',') + 1).trim();
    }

    protected void setLatestPayment(String s) {
        this.latestPayment = LocalDate.parse(s);
    }

}
