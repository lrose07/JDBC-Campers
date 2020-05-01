/**
 * The Camper object
 *
 * @author Lauren Rose
 * @version 1-May-2020
 *
 * Radford University, Dept of IT
 */
public class Camper {

    private final int camperID;
    private String firstName;
    private String lastName;
    private String nickName;
    private double campStoreBudget;
    private double campStoreSpent;
    private int revNum;

    /**
     * Constructor for a camper with an id
     * @param id
     */
    Camper(int id) {
        this.camperID = id;
    }

    /**
     * Constructor for a fully populated camper
     * @param id camper id
     * @param fName camper first name
     * @param lName camper last name
     * @param nName camper nickname
     * @param csBudget camper store budget
     * @param csSpent camper store spent
     * @param revNum revision number
     */
    Camper(int id, String fName, String lName, String nName, double csBudget, double csSpent, int revNum) {
        this.camperID = id;
        this.firstName = fName;
        this.lastName = lName;
        this.nickName = nName;
        this.campStoreBudget = csBudget;
        this.campStoreSpent = csSpent;
        this.revNum = revNum;
    }

    /**
     * Formats a camper object for print
     * @return formatted string
     */
    @Override
    public String toString() {
        return "Camper #: " + camperID +
                "\n Name: " + firstName + " " + lastName +
                "\n Goes by: " + nickName +
                "\n Can spend $" + campStoreBudget + " total." +
                "\n Has spent $" + campStoreSpent + " so far." +
                "\n Has $" + (campStoreBudget - campStoreSpent) + " remaining." +
                "\n This camper info has been revised " + revNum + " times.";
    }

    /**
     * Get camper ID
     * @return camper ID
     */
    public int getCamperID() {
        return camperID;
    }

    /**
     * Gets camper first name
     * @return camper first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets camper first name
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets camper last name
     * @return camper last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets camper last name
     * @param lastName new camper last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets camper nickname
     * @return camper nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets new camper nickname
     * @param nickName new nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Get camp store budget
     * @return camp store budget
     */
    public double getCampStoreBudget() {
        return campStoreBudget;
    }

    /**
     * Set camp store budget
     * @param campStoreBudget new camp store budget
     */
    public void setCampStoreBudget(double campStoreBudget) {
        this.campStoreBudget = campStoreBudget;
    }

    /**
     * Get camp store spent
     * @return Camp store spent
     */
    public double getCampStoreSpent() {
        return campStoreSpent;
    }

    /**
     * Set camp store spent
     * @param campStoreSpent new camp store spent
     */
    public void setCampStoreSpent(double campStoreSpent) {
        this.campStoreSpent = campStoreSpent;
    }

    /**
     * Get revision number
     * @return revision number
     */
    public int getRevNum() {
        return revNum;
    }

    /**
     * Set revision number
     * Intended only for the initial creation of a camper
     * @param revNum new revision number
     */
    public void setRevNum(int revNum) {
        this.revNum = revNum;
    }
}
