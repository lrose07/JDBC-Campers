import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class CamperDataTerminal extends JFrame {

    private final CamperDataController controller;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private JPanel headerPanel;
    private JPanel inputPanel;

    private JTextField camperSearchBox;
    private JButton searchButton;
    private JLabel searchStatus;
    private JLabel camperIdLabel;
    private JTextField camperFirstNameBox;
    private JTextField camperLastNameBox;
    private JTextField camperNickNameBox;
    private JTextField camperStoreBudgetBox;
    private JTextField camperStoreSpentBox;
    private JLabel camperRevNumLabel;
    private JButton saveButton;
    private JButton clearButton;

    private Camper currentCamper;

    CamperDataTerminal(CamperDataController controller) {
        this.controller = controller;

        // main panel
        JPanel mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(0, 100, 0));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 600));
        mainPanel.setMinimumSize(new Dimension(500, 600));

        configureHeaderPanel();
        configureInputPanel();

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
        camperIdLabel.setText("Camper ID: " + currentCamper.getCamperID());
        camperFirstNameBox.setText(currentCamper.getFirstName());
        camperLastNameBox.setText(currentCamper.getLastName());
        camperNickNameBox.setText(currentCamper.getNickName());
        camperStoreBudgetBox.setText(String.valueOf(currentCamper.getCampStoreBudget()));
        camperStoreSpentBox.setText(String.valueOf(currentCamper.getCampStoreSpent()));
        camperRevNumLabel.setText("RevNum: " + currentCamper.getRevNum());
    }

    void saveClicked() {
        if (!camperFirstNameBox.getText().equals(currentCamper.getFirstName())) {
            currentCamper.setFirstName(camperFirstNameBox.getText());
        }
        if (!camperLastNameBox.getText().equals(currentCamper.getLastName())) {
            currentCamper.setLastName(camperLastNameBox.getText());
        }
        if (!camperNickNameBox.getText().equals(currentCamper.getNickName())) {
            currentCamper.setNickName(camperNickNameBox.getText());
        }
        if (currentCamper.getCampStoreBudget() != Double.parseDouble(camperStoreBudgetBox.getText())) {
            currentCamper.setCampStoreBudget(Double.parseDouble(camperStoreBudgetBox.getText()));
        }
        if (currentCamper.getCampStoreSpent() != Double.parseDouble(camperStoreSpentBox.getText())) {
            currentCamper.setCampStoreSpent(Double.parseDouble(camperStoreSpentBox.getText()));
        }
        controller.processEditCamper(currentCamper);
    }

    void configureHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(500, 50));
        headerPanel.setMaximumSize(new Dimension(500, 50));

        JLabel welcomeLabel = new JLabel("Welcome to the Camper Info System!");

        headerPanel.add(welcomeLabel);
    }

    void configureInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(500, 425));
        inputPanel.setMinimumSize(new Dimension(500, 425));
        inputPanel.setLayout(new GridLayout(8, 2));

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

        camperRevNumLabel = new JLabel("RevNum: ");

        saveButton = new JButton("Save changes");
        saveButton.addActionListener(e -> saveClicked());

        clearButton = new JButton("Clear form");
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
        camperIdLabel.setText("Camper ID: ");
        camperFirstNameBox.setText("");
        camperLastNameBox.setText("");
        camperNickNameBox.setText("");
        camperStoreBudgetBox.setText("");
        camperStoreSpentBox.setText("");
        camperRevNumLabel.setText("RevNum: ");
    }
}
