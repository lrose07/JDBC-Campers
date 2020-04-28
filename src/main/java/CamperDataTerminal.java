import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;

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

    CamperDataTerminal(CamperDataController controller) {
        this.controller = controller;

        // main panel
        JPanel mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(0, 100, 0));
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

    void searchClicked() {
        try {
            currentCamper = controller.findCamperById(Integer.parseInt(camperSearchBox.getText()));
        } catch (NumberFormatException e) {
            currentCamper = controller.findCamperByName(camperSearchBox.getText());
        }
        if (currentCamper != null) {
            updateFields();
        } else {
            System.out.println("No camper found");
            //todo: make this a gui notification, not terminal
        }
    }

    void updateFields() {
        camperIdLabel.setText(CAMPER_ID_STRING + currentCamper.getCamperID());
        camperFirstNameBox.setText(currentCamper.getFirstName());
        camperLastNameBox.setText(currentCamper.getLastName());
        camperNickNameBox.setText(currentCamper.getNickName());
        camperStoreBudgetBox.setText(String.valueOf(currentCamper.getCampStoreBudget()));
        camperStoreSpentBox.setText(String.valueOf(currentCamper.getCampStoreSpent()));
        camperRevNumLabel.setText(REV_NUM_STRING + currentCamper.getRevNum());
    }

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
        if (currentCamper.getCampStoreBudget() != Double.parseDouble(camperStoreBudgetBox.getText())) {
            System.out.println("Updating budget...");
            currentCamper.setCampStoreBudget(Double.parseDouble(camperStoreBudgetBox.getText()));
        }
        if (currentCamper.getCampStoreSpent() != Double.parseDouble(camperStoreSpentBox.getText())) {
            System.out.println("Updating spent amount...");
            currentCamper.setCampStoreSpent(Double.parseDouble(camperStoreSpentBox.getText()));
        }
        System.out.println("Saving to db...");
        controller.processEditCamper(currentCamper);
    }

    void configureHeaderPanel() {
        final int panelHeight = 50;
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, panelHeight));
        headerPanel.setMaximumSize(new Dimension(WINDOW_WIDTH, panelHeight));

        JLabel welcomeLabel = new JLabel("Welcome to the Camper Info System!");

        headerPanel.add(welcomeLabel);
    }

    void configureInputPanel() {
        final int panelHeight = 425;
        final int gridRows = 8;
        final int gridColumns = 2;
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, panelHeight));
        inputPanel.setMinimumSize(new Dimension(WINDOW_WIDTH, panelHeight));
        inputPanel.setLayout(new GridLayout(gridRows, gridColumns));

        camperSearchBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("Enter search here", camperSearchBox);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchClicked());

        camperIdLabel = new JLabel(CAMPER_ID_STRING);

        camperFirstNameBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("First name", camperFirstNameBox);

        camperLastNameBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("Last name", camperLastNameBox);

        camperNickNameBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("Nickname", camperNickNameBox);

        camperStoreBudgetBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("Store budget", camperStoreBudgetBox);

        camperStoreSpentBox = new JTextField(TEXT_BOX_COLUMNS);
        PromptSupport.setPrompt("Store spent", camperStoreSpentBox);

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

    void clearClicked() {
        camperSearchBox.setText("");
        PromptSupport.setPrompt("Enter search here", camperSearchBox);
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
