import java.sql.*;
import java.util.HashMap;

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
                newCamper.setFirstName(resultSet.getString("firstname"));
                newCamper.setLastName(resultSet.getString("lastname"));
                newCamper.setNickName(resultSet.getString("nickname"));
                newCamper.setCampStoreBudget(resultSet.getDouble("campstorebudget"));
                newCamper.setCampStoreSpent(resultSet.getDouble("campstorespent"));
                newCamper.setRevNum(resultSet.getInt("revnum"));
            }
        } catch (SQLException e) {
            System.out.println("failed query: " + e.getMessage());
        }

        return newCamper;
    }

    Camper getCamperByFirstName(String firstName) {
        Camper newCamper = null;
        String sqlQuery = String.format("SELECT * FROM campers WHERE firstname = '%s'", stringUpcaseFirst(firstName));
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int cId = resultSet.getInt("cid");
                String lName = resultSet.getString("lastname");
                String nName = resultSet.getString("nickname");
                double budget = resultSet.getDouble("campstorebudget");
                double spent = resultSet.getDouble("campstorespent");
                int revnum = resultSet.getInt("revnum");
                newCamper = new Camper(cId, firstName, lName, nName, budget, spent, revnum);
            }
        } catch (SQLException e) {
            System.out.println("failed query: " + e.getMessage());
        }

        return newCamper;
    }

    Camper getCamperByLastName(String lastName) {
        Camper newCamper = null;
        String sqlQuery = String.format("SELECT * FROM campers WHERE lastname = '%s'", stringUpcaseFirst(lastName));
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int cId = resultSet.getInt("cid");
                String fName = resultSet.getString("firstname");
                String nName = resultSet.getString("nickname");
                double budget = resultSet.getDouble("campstorebudget");
                double spent = resultSet.getDouble("campstorespent");
                int revnum = resultSet.getInt("revnum");
                newCamper = new Camper(cId, fName, lastName, nName, budget, spent, revnum);
            }
        } catch (SQLException e) {
            System.out.println("failed query: " + e.getMessage());
        }

        return newCamper;
    }

    void editCamper(Camper c) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE campers SET ");
        sb.append("firstname = '").append(c.getFirstName()).append("'");
        sb.append("lastname = ").append(c.getLastName()).append("'");
        sb.append("nickname = ").append(c.getNickName()).append("'");
        sb.append("campstorebudget = ").append(c.getCampStoreBudget()).append("'");
        sb.append("campstorespent = ").append(c.getCampStoreSpent()).append("'");
        sb.append("WHERE cid = ").append(c.getCamperID());

        String sqlQuery = sb.toString();
        try(Statement statement = con.createStatement()) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            //todo:handle
        }
    }

    String stringUpcaseFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
