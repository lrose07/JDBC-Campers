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
            // todo: handle
        }
    }

    Camper getCamper(int id) {
        String sqlQuery = String.format("SELECT * FROM campers WHERE cid = %d", id);
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            System.out.println(resultSet.toString());
        } catch (SQLException e) {
            // todo: handle
        }

        return null;
    }

    void saveCamper(Camper newCamper) {
        new Camper(
                newCamper.getCamperID(),
                newCamper.getFirstName(),
                newCamper.getLastName(),
                newCamper.getNickName(),
                newCamper.getCampStoreBudget(),
                newCamper.getCampStoreSpent()
        );
    }
}
