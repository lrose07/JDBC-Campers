import java.sql.*;

/**
 * This class is the link between the GUI and database connection for
 * the camper information system.
 *
 * @author Lauren Rose
 * @version 1-May-2020
 *
 * Radford University, Dept of IT
 */
public class CamperFactory {

    private Connection con;

    /**
     * General constructor
     */
    CamperFactory() {}

    /**
     * Establishes the db connection
     */
    void connect() {
        final String url = "jdbc:oracle:thin:@worf.radford.edu:1521:itec3";
        final String user = "larose";
        final String password = "bmwDr1v3r$$";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("failed connection: " + e.getMessage());
        }
        //todo: save u/n, pw to env somewhere, shouldn't be hardcoded
    }

    /**
     * Searches for a camper
     * @param id the id of the camper to search for
     * @return the camper that was found
     */
    Camper getCamperById(int id) {
        Camper newCamper = null;
        String sqlQuery = String.format("SELECT * FROM campers WHERE cid = %d", id);
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                newCamper = new Camper(id);
                newCamper.setFirstName(resultSet.getString(F_NAME_STR));
                newCamper.setLastName(resultSet.getString(L_NAME_STR));
                newCamper.setNickName(resultSet.getString(N_NAME_STR));
                newCamper.setCampStoreBudget(resultSet.getDouble(BUDGET_STR));
                newCamper.setCampStoreSpent(resultSet.getDouble(SPENT_STR));
                newCamper.setRevNum(resultSet.getInt(REV_NUM_STR));
            }
        } catch (SQLException e) {
            System.out.println(FAILED + e.getMessage());
        }

        return newCamper;
    }

    /**
     * Searches for a camper
     * @param firstName the first name of the camper to search for
     * @return the camper that was found
     */
    Camper getCamperByFirstName(String firstName) {
        String sqlQuery = String.format("SELECT * FROM campers WHERE firstname = '%s'", stringUpcaseFirst(firstName));
        return generateCamper(sqlQuery);
    }

    /**
     * Searches for a camper
     * @param lastName the last name of the camper to search for
     * @return the camper that was found
     */
    Camper getCamperByLastName(String lastName) {
        String sqlQuery = String.format("SELECT * FROM campers WHERE lastname = '%s'", stringUpcaseFirst(lastName));
        return generateCamper(sqlQuery);
    }

    /**
     * Makes a new camper object from a found database record
     * @param sqlQuery query to search for camper
     * @return the camper found by the search criteria, or null if none found
     */
    Camper generateCamper(String sqlQuery) {
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            if (resultSet.next()) {
                int cId = resultSet.getInt(ID_STR);
                String fName = resultSet.getString(F_NAME_STR);
                String lName = resultSet.getString(L_NAME_STR);
                String nName = resultSet.getString(N_NAME_STR);
                double budget = resultSet.getDouble(BUDGET_STR);
                double spent = resultSet.getDouble(SPENT_STR);
                int revnum = resultSet.getInt(REV_NUM_STR);
                return new Camper(cId, fName, lName, nName, budget, spent, revnum);
            }
        } catch (SQLException e) {
            System.out.println(FAILED + e.getMessage());
        }
        return null;
    }

    /**
     * Changes one or more fields of a record
     * @param c the camper to change
     */
    void editCamper(Camper c) {
        System.out.println("Beginning db update...");
        String sqlQuery = String.format(
                "UPDATE campers SET " +
                        "firstname = '%s', " +
                        "lastname = '%s', " +
                        "nickname = '%s', " +
                        "campstorebudget = %f, " +
                        "campstorespent = %f " +
                        "WHERE cid = %d",
                c.getFirstName(),
                c.getLastName(),
                c.getNickName(),
                c.getCampStoreBudget(),
                c.getCampStoreSpent(),
                c.getCamperID());
        try(Statement statement = con.createStatement()) {
            System.out.println("Rows updated: " + statement.executeUpdate(sqlQuery));
        } catch (SQLException e) {
            System.out.println("Something went wrong\n" + e);
        }
    }

    /**
     * Capitalized the first letter of a string, and lowercases the rest
     * @param s incoming string
     * @return formatted string
     */
    String stringUpcaseFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private static final String FAILED = "failed query: ";
    private static final String ID_STR = "cid";
    private static final String F_NAME_STR = "firstname";
    private static final String L_NAME_STR = "lastname";
    private static final String N_NAME_STR = "nickname";
    private static final String BUDGET_STR = "campstorebudget";
    private static final String SPENT_STR = "campstorespent";
    private static final String REV_NUM_STR = "revnum";
}
