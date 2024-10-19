public enum MemberStatus {
    MEMBER("medlem"),
    FORMER_MEMBER("tidigare medlem"),
    NOT_MEMBER("inte medlem");

    public final String status;

    MemberStatus(String memberStatus) {
        this.status = memberStatus;
    }
}
