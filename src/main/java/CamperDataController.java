public class CamperDataController {
    CamperDataController() {
//        dataConnect();
        makeGui();
    }

    void dataConnect() {
        CamperFactory cf = new CamperFactory();
        cf.connect();
    }

    void makeGui() {
        new CamperDataTerminal(this);
    }

    void processEditCamper() {
        throw new UnsupportedOperationException();
    }
}
