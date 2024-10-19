import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckIfMember {

    private ArrayList<GymMembers> listOfMembers = new ArrayList<>();

    protected ArrayList<GymMembers> readMembersList(Path path) {
        try (Scanner scan = new Scanner(path.toFile())) {
            String firstLine = "";
            String secondLine = "";

            while (scan.hasNext()) {
                GymMembers gm = new GymMembers();
                firstLine = scan.nextLine();
                gm.setPersonalInfo(firstLine.trim());

                if (scan.hasNext()) {
                    secondLine = scan.nextLine();
                    gm.setLatestPayment(secondLine.trim());
                    listOfMembers.add(gm);
                }
            }
        } catch (IOException e) {
            System.out.println("Kunde inte läsa filen. \n" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfMembers;
    }

    public String compareCustomerInfo(boolean isTest, String testData){
        String customerInfoToCompare = "";
        if (isTest){
                Scanner input = new Scanner(testData);
                return input.nextLine();
            } else {
                Scanner input = new Scanner(System.in);
                System.out.println("Hej! För att kontrollera medlemskap ange fullständigt namn eller " +
                        "personnumer. Tryck Enter för att avsluta.");

                customerInfoToCompare = input.nextLine();
            }
        return customerInfoToCompare;
    }


    /*
    protected boolean isOnMembersList(String customerInfo) {
        for (GymMembers gm : listOfMembers) {
            if (customerInfo.equals(gm.getName()) || customerInfo.equalsIgnoreCase(gm.getSocialSecurityNumber())) {
                return true;
            }
        }
        return false;
    }

     */

    protected boolean isMembershipValid(GymMembers gm) {
        return gm.getLatestPayment().isAfter(LocalDate.now().minusYears(1));
    }
/*
    protected boolean validateMemberStatus(String customerInfo) {
        VisitLog vl = new VisitLog();
        for (GymMembers gm : listOfMembers) {
            if (customerInfo.equals(gm.getName()) || customerInfo.equalsIgnoreCase(gm.getSocialSecurityNumber())) {
                if (isMembershipValid(gm)) {
                    vl.writeVisitToFile(gm, false);
                    return true;
                }
            }
        }
        return false;
    }

 */

    public MemberStatus checkMemberStatus(String customerInfo, ArrayList<GymMembers> gmList) {
        VisitLog vl = new VisitLog();
        for (GymMembers gm : gmList) {
            if (customerInfo.equals(gm.getName()) || customerInfo.equalsIgnoreCase(gm.getSocialSecurityNumber())) {
                if (isMembershipValid(gm)) {
                    vl.writeVisitToFile(gm, false);
                    return MemberStatus.MEMBER;
                }else {
                    return MemberStatus.FORMER_MEMBER;
                }
            }
        }
        return MemberStatus.NOT_MEMBER;
    }

    public String printMembershipStatus(MemberStatus memberStatus) {
        return "Personen är " + memberStatus.status + ".";
    }

}
