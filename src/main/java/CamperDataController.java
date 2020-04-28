public class CamperDataController {

    private CamperFactory cf;
    private int revNumOriginal;
    private int camperId;

    CamperDataController() {
        dataConnect();
        makeGui();
//        System.out.println(cf.getCamper(103).toString());

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

    void processEditCamper(Camper newCamper) {
        Camper cmpr2 = cf.getCamperById(camperId);
        if (cmpr2.getRevNum() == revNumOriginal) {
            cf.editCamper(newCamper);
        }
    }

//    void processAddCamper() {
//
//    }
}
