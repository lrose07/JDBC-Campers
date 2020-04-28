import java.sql.*;

public class CamperFactory {

    private Connection con;

    CamperFactory() {

    }

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
    }

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

    Camper getCamperByFirstName(String firstName) {
        Camper newCamper = null;
        String sqlQuery = String.format("SELECT * FROM campers WHERE firstname = '%s'", stringUpcaseFirst(firstName));
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int cId = resultSet.getInt(ID_STR);
                String lName = resultSet.getString(L_NAME_STR);
                String nName = resultSet.getString(N_NAME_STR);
                double budget = resultSet.getDouble(BUDGET_STR);
                double spent = resultSet.getDouble(SPENT_STR);
                int revnum = resultSet.getInt(REV_NUM_STR);
                newCamper = new Camper(cId, firstName, lName, nName, budget, spent, revnum);
            }
        } catch (SQLException e) {
            System.out.println(FAILED + e.getMessage());
        }

        return newCamper;
    }

    Camper getCamperByLastName(String lastName) {
        Camper newCamper = null;
        String sqlQuery = String.format("SELECT * FROM campers WHERE lastname = '%s'", stringUpcaseFirst(lastName));
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int cId = resultSet.getInt(ID_STR);
                String fName = resultSet.getString(F_NAME_STR);
                String nName = resultSet.getString(N_NAME_STR);
                double budget = resultSet.getDouble(BUDGET_STR);
                double spent = resultSet.getDouble(SPENT_STR);
                int revnum = resultSet.getInt(REV_NUM_STR);
                newCamper = new Camper(cId, fName, lastName, nName, budget, spent, revnum);
            }
        } catch (SQLException e) {
            System.out.println(FAILED + e.getMessage());
        }

        return newCamper;
    }

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
