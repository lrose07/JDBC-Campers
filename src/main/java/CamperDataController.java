/**
 * This class is the link between the GUI and database connection for
 * the camper information system.
 *
 * @author Lauren Rose
 * @version 1-May-2020
 *
 * Radford University, Dept of IT
 */
public class CamperDataController {

    private CamperFactory cf;
    private int revNumOriginal;
    private int camperId;

    /**
     * Generic constructor. Initiates GUI and DB connection.
     */
    CamperDataController() {
        dataConnect();
        makeGui();
    }

    /**
     * Establishes connection to the database
     */
    void dataConnect() {
        cf = new CamperFactory();
        cf.connect();
    }

    /**
     * Builds a GUI window
     */
    void makeGui() {
        new CamperDataTerminal(this);
    }

    /**
     * Finds a camper by their ID number
     * @param id camper ID to search
     * @return the found camper, or null if none was found
     */
    Camper findCamperById(int id) {
        Camper cmpr = cf.getCamperById(id);
        getInitialData(cmpr);
        return cmpr;
    }

    /**
     * Finds camper by their name
     * @param name the name to search
     * @return the found camper, or null if none was found
     */
    Camper findCamperByName(String name) {
        Camper cmpr = cf.getCamperByFirstName(name);
        if (cmpr == null) {
            cmpr = cf.getCamperByLastName(name);
        }
        getInitialData(cmpr);
        return cmpr;
        //todo: handle the case of multiple campers found with same name
    }

    /**
     * Gets and stores db revision count when camper data
     * is first pulled
     * @param c camper to grab
     */
    void getInitialData(Camper c) {
        if (c != null) {
            camperId = c.getCamperID();
            revNumOriginal = c.getRevNum();
        }
    }

    /**
     * Checks that the camper row has not been modified since data was pulled
     * @param newCamper the edited camper to save
     * @return the updated revnum, or -1 if update was unsuccessful
     */
    int processEditCamper(Camper newCamper) {
        Camper cmpr2 = cf.getCamperById(camperId);
        if (cmpr2.getRevNum() == revNumOriginal) {
            System.out.println("RevNum check passed.\nUpdating camper...");
            cf.editCamper(newCamper);
            return cmpr2.getRevNum();
        }
        return -1;
    }

    /**
     * STUB
     * Processes saving a new camper to the db
     */
    void processAddCamper() {
        throw new UnsupportedOperationException();
    }
}
