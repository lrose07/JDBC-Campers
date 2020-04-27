import org.jdesktop.swingx.StackLayout;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class CamperDataTerminal extends JFrame {

    private CamperDataController controller;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel inputPanel;
    private JPanel searchPanel;
    private JPanel addPanel;

    private JTextField camperSearchBox;
    private JButton searchButton;
    private JLabel searchStatus;
    private JLabel camperIdLabel;
    private JTextField camperFirstNameBox;
    private JTextField camperLastNameBox;
    private JTextField camperNickNameBox;
    private JTextField camperStoreBudgetBox;
    private JTextField camperStoreSpentBox;
    private JLabel camperRevNumLabelBox;
    private JButton saveButton;
    private JButton clearButton;

    CamperDataTerminal(CamperDataController controller) {
        this.controller = controller;

        // main panel
        mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(0, 100, 0));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 600));
        mainPanel.setMinimumSize(new Dimension(500, 600));

        buildHeaderPanel();
        buildInputPanel();
        buildAddPanel();
        buildSearchPanel();

        // add sub panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // set frame properties
        this.setPreferredSize(new Dimension(500, 600));
        this.setMinimumSize(new Dimension(500, 600));
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void searchClicked() {
        logger.log(Level.INFO, camperSearchBox.getText());
    }

    void saveClicked() {
        logger.log(Level.INFO, camperFirstNameBox.getText());
//        controller.processEditCamper();
    }

    void buildHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(500, 150));
        headerPanel.setMaximumSize(new Dimension(500, 150));

        JLabel welcomeLabel = new JLabel("Welcome to the Camper Info System!");

        JButton addCamperButton = new JButton("Add a camper");
        addCamperButton.addActionListener(e -> addCamperClicked());

        JButton editCamperButton = new JButton("View/edit a camper");
        editCamperButton.addActionListener(e -> editCamperClicked());
        headerPanel.add(welcomeLabel);
        headerPanel.add(addCamperButton);
        headerPanel.add(editCamperButton);
    }

    void buildInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(500, 425));
        inputPanel.setMinimumSize(new Dimension(500, 425));
        inputPanel.setLayout(new StackLayout());
    }

    void buildSearchPanel() {
        searchPanel = new JPanel();

        camperSearchBox = new JTextField(10);
        PromptSupport.setPrompt("Enter search here", camperSearchBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchClicked());

        camperIdLabel = new JLabel("Camper ID: ");

        camperFirstNameBox = new JTextField();
        PromptSupport.setPrompt("First name", camperFirstNameBox);

        camperLastNameBox = new JTextField();
        PromptSupport.setPrompt("Last name", camperLastNameBox);

        camperNickNameBox = new JTextField();
        PromptSupport.setPrompt("Nickname", camperNickNameBox);

        camperStoreBudgetBox = new JTextField();
        PromptSupport.setPrompt("Store budget", camperStoreBudgetBox);

        camperStoreSpentBox = new JTextField();
        PromptSupport.setPrompt("Store spent", camperStoreSpentBox);

        camperRevNumLabelBox = new JLabel("RevNum: ");

        saveButton = new JButton("Save changes");
        saveButton.addActionListener(e -> saveClicked());

        clearButton = new JButton("Clear form");
        clearButton.addActionListener(e -> clearClicked());

        searchPanel.add(camperSearchBox);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("Camper Info:"));
        searchPanel.add(camperIdLabel);
        searchPanel.add(camperFirstNameBox);
        searchPanel.add(camperLastNameBox);
        searchPanel.add(camperNickNameBox);
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel("Camper Finances:"));
        searchPanel.add(new JLabel(""));
        searchPanel.add(camperStoreBudgetBox);
        searchPanel.add(camperStoreSpentBox);
        searchPanel.add(camperRevNumLabelBox);
        searchPanel.add(new JLabel(""));

        searchPanel.add(saveButton);
        searchPanel.add(clearButton);
    }

    void buildAddPanel() {

    }

    void addCamperClicked() {

    }

    void editCamperClicked() {

    }

    void clearClicked() {
        camperSearchBox.setText("");
        PromptSupport.setPrompt("Enter search here", camperSearchBox);
        camperIdLabel.setText("Camper ID: ");
        camperFirstNameBox.setText("");
        camperLastNameBox.setText("");
        camperNickNameBox.setText("");
        camperStoreBudgetBox.setText("");
        camperStoreSpentBox.setText("");
        camperRevNumLabelBox.setText("RevNum: ");
    }
}
