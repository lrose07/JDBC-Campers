public class Camper {

    private final int camperID;
    private String firstName;
    private String lastName;
    private String nickName;
    private double campStoreBudget;
    private double campStoreSpent;

    Camper(int id, String fName, String lName, String nName, double csBudget, double csSpent) {
        this.camperID = id;
        this.firstName = fName;
        this.lastName = lName;
        this.nickName = nName;
        this.campStoreBudget = csBudget;
        this.campStoreSpent = csSpent;
    }


    public int getCamperID() {
        return camperID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getCampStoreBudget() {
        return campStoreBudget;
    }

    public void setCampStoreBudget(double campStoreBudget) {
        this.campStoreBudget = campStoreBudget;
    }

    public double getCampStoreSpent() {
        return campStoreSpent;
    }

    public void setCampStoreSpent(double campStoreSpent) {
        this.campStoreSpent = campStoreSpent;
    }
}
