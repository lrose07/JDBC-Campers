public class CamperDataController {

    private CamperFactory cf;

    CamperDataController() {
        dataConnect();
//        makeGui();
        cf.getCamper(104);

    }

    void dataConnect() {
        cf = new CamperFactory();
        cf.connect();
    }

    void makeGui() {
        new CamperDataTerminal(this);
    }

    void processEditCamper() {
        throw new UnsupportedOperationException();
    }

    void processAddCamper() {

    }
}
