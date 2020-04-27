import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;

class CamperDataTerminal extends JFrame {

    private JPanel mainPanel;
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
    private JButton cancelButton;

    CamperDataTerminal() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setMinimumSize(new Dimension(500, 500));


        camperSearchBox = new JTextField(10);
        PromptSupport.setPrompt("test", camperSearchBox);

        mainPanel.add(camperSearchBox);

        this.setPreferredSize(new Dimension(500, 500));
        this.setMinimumSize(new Dimension(500, 500));
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
