public class CamperDataController {

    private CamperFactory cf;
    private int revNumOriginal;
    private int camperId;

    CamperDataController() {
        dataConnect();
        makeGui();
    }

    void dataConnect() {
        cf = new CamperFactory();
        cf.connect();
    }

    void makeGui() {
        new CamperDataTerminal(this);
    }

    Camper findCamperById(int id) {
        Camper cmpr = cf.getCamperById(id);
        getInitialData(cmpr);
        return cmpr;
    }

    Camper findCamperByName(String name) {
        Camper cmpr = cf.getCamperByFirstName(name);
        if (cmpr == null) {
            cmpr = cf.getCamperByLastName(name);
        }
        getInitialData(cmpr);
        return cmpr;
    }

    void getInitialData(Camper c) {
        if (c != null) {
            camperId = c.getCamperID();
            revNumOriginal = c.getRevNum();
        }
    }

    int processEditCamper(Camper newCamper) {
        Camper cmpr2 = cf.getCamperById(camperId);
        if (cmpr2.getRevNum() == revNumOriginal) {
            System.out.println("RevNum check passed.\nUpdating camper...");
            cf.editCamper(newCamper);
            return cmpr2.getRevNum();
        }
        return -1;
    }

//    void processAddCamper() {
//
//    }
}
