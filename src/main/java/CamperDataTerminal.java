//import org.jdesktop.swingx.prompt.PromptSupport;
import javax.swing.*;
import java.awt.*;

/**
 * This class contains the graphical user interface for the camper
 * information system. It allows a user to search for a camper and edit
 * the camper's information.
 *
 * @author Lauren Rose
 * @version 1-May-2020
 *
 * Radford University, Dept of IT
 */
class CamperDataTerminal extends JFrame {

    private final CamperDataController controller;

    private JPanel headerPanel;
    private JPanel inputPanel;

    private JTextField camperSearchBox;
    private JLabel camperIdLabel;
    private JTextField camperFirstNameBox;
    private JTextField camperLastNameBox;
    private JTextField camperNickNameBox;
    private JTextField camperStoreBudgetBox;
    private JTextField camperStoreSpentBox;
    private JLabel camperRevNumLabel;

    private Camper currentCamper;

    /**
     * Constructor, creates the GUI on the screen
     * @param controller controller
     */
    CamperDataTerminal(CamperDataController controller) {
        this.controller = controller;

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        configureHeaderPanel();
        configureInputPanel();

        // add sub panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // set frame properties
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Fires when the search button is clicked
     * Processes the findCamperBy[value]
     */
    void searchClicked() {
        try {
            currentCamper = controller.findCamperById(Integer.parseInt(camperSearchBox.getText()));
        } catch (NumberFormatException e) {
            currentCamper = controller.findCamperByName(camperSearchBox.getText());
        }
        if (currentCamper != null) {
            updateFields();
        } else {
            clearClicked();
            System.out.println("No camper found");
            //todo: make this a gui notification, not terminal
        }
    }

    /**
     * Populates form with found camper data
     */
    void updateFields() {
        camperIdLabel.setText(CAMPER_ID_STRING + currentCamper.getCamperID());
        camperFirstNameBox.setText(currentCamper.getFirstName());
        camperLastNameBox.setText(currentCamper.getLastName());
        camperNickNameBox.setText(currentCamper.getNickName());
        camperStoreBudgetBox.setText(String.valueOf(currentCamper.getCampStoreBudget()));
        camperStoreSpentBox.setText(String.valueOf(currentCamper.getCampStoreSpent()));
        camperRevNumLabel.setText(REV_NUM_STRING + currentCamper.getRevNum());
    }

    /**
     * Triggers when save button clicked
     * Processes the save to the database
     */
    void saveClicked() {
        if (!camperFirstNameBox.getText().equals(currentCamper.getFirstName())) {
            System.out.println("Updating first name...");
            currentCamper.setFirstName(camperFirstNameBox.getText());
        }
        if (!camperLastNameBox.getText().equals(currentCamper.getLastName())) {
            System.out.println("Updating last name...");
            currentCamper.setLastName(camperLastNameBox.getText());
        }
        if (!camperNickNameBox.getText().equals(currentCamper.getNickName())) {
            System.out.println("Updating nickname...");
            currentCamper.setNickName(camperNickNameBox.getText());
        }
        try {
            if (currentCamper.getCampStoreBudget() != Double.parseDouble(camperStoreBudgetBox.getText())) {
                System.out.println("Updating budget...");
                currentCamper.setCampStoreBudget(Double.parseDouble(camperStoreBudgetBox.getText()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid budget");
            camperStoreBudgetBox.setText(String.valueOf(currentCamper.getCampStoreBudget()));
        }
        try {
            if (currentCamper.getCampStoreSpent() != Double.parseDouble(camperStoreSpentBox.getText())) {
                System.out.println("Updating spent amount...");
                currentCamper.setCampStoreSpent(Double.parseDouble(camperStoreSpentBox.getText()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid spent");
            camperStoreSpentBox.setText(String.valueOf(currentCamper.getCampStoreSpent()));
        }
        System.out.println("Saving to db...");
        int revNum = controller.processEditCamper(currentCamper);
        if (revNum > 0) {
            camperRevNumLabel.setText(REV_NUM_STRING + (revNum + 1));
        }
    }

    /**
     * Creates header panel
     */
    void configureHeaderPanel() {
        final int panelHeight = 50;
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, panelHeight));
        headerPanel.setMaximumSize(new Dimension(WINDOW_WIDTH, panelHeight));

        JLabel welcomeLabel = new JLabel("Welcome to the Camper Info System!\n");
        JLabel instructionLabel = new JLabel("Search for a camper by ID, first name, or last name");

        headerPanel.add(welcomeLabel);
        headerPanel.add(instructionLabel);
    }

    /**
     * Creates input panel
     */
    void configureInputPanel() {
        final int panelHeight = 425;
        final int gridRows = 8;
        final int gridColumns = 2;

        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, panelHeight));
        inputPanel.setMinimumSize(new Dimension(WINDOW_WIDTH, panelHeight));
        inputPanel.setLayout(new GridLayout(gridRows, gridColumns));

        camperSearchBox = new JTextField(TEXT_BOX_COLUMNS);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchClicked());

        camperIdLabel = new JLabel(CAMPER_ID_STRING);
        camperFirstNameBox = new JTextField(TEXT_BOX_COLUMNS);
        camperLastNameBox = new JTextField(TEXT_BOX_COLUMNS);
        camperNickNameBox = new JTextField(TEXT_BOX_COLUMNS);
        camperStoreBudgetBox = new JTextField(TEXT_BOX_COLUMNS);
        camperStoreSpentBox = new JTextField(TEXT_BOX_COLUMNS);

        // To run without Gradle, comment out this block
        // as well as the PromptSupport import statement, line 1
//        PromptSupport.setPrompt("Enter search here", camperSearchBox);
//        PromptSupport.setPrompt("First name", camperFirstNameBox);
//        PromptSupport.setPrompt("Last name", camperLastNameBox);
//        PromptSupport.setPrompt("Nickname", camperNickNameBox);
//        PromptSupport.setPrompt("Store budget", camperStoreBudgetBox);
//        PromptSupport.setPrompt("Store spent", camperStoreSpentBox);
        // */

        camperRevNumLabel = new JLabel(REV_NUM_STRING);

        JButton saveButton = new JButton("Save changes");
        saveButton.addActionListener(e -> saveClicked());

        JButton clearButton = new JButton("Clear form");
        clearButton.addActionListener(e -> clearClicked());

        inputPanel.add(camperSearchBox);
        inputPanel.add(searchButton);
        inputPanel.add(new JLabel("Camper Info:"));
        inputPanel.add(camperIdLabel);
        inputPanel.add(camperFirstNameBox);
        inputPanel.add(camperLastNameBox);
        inputPanel.add(camperNickNameBox);
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel("Camper Finances:"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(camperStoreBudgetBox);
        inputPanel.add(camperStoreSpentBox);
        inputPanel.add(camperRevNumLabel);
        inputPanel.add(new JLabel(""));

        inputPanel.add(saveButton);
        inputPanel.add(clearButton);
    }

    /**
     * Clears all form fields, except Search box
     */
    void clearClicked() {
        camperIdLabel.setText(CAMPER_ID_STRING);
        camperFirstNameBox.setText("");
        camperLastNameBox.setText("");
        camperNickNameBox.setText("");
        camperStoreBudgetBox.setText("");
        camperStoreSpentBox.setText("");
        camperRevNumLabel.setText(REV_NUM_STRING);
    }

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int TEXT_BOX_COLUMNS = 20;
    private static final String CAMPER_ID_STRING = "Camper ID: ";
    private static final String REV_NUM_STRING = "RevNum: ";
}
