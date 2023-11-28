package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// panel with main menu and options
public class MainMenuPanel extends JPanel implements PanelStyle, ActionListener {
    BookBuddyApp appFrame;
    ReadListPanel readListPanel;
    JPanel centrePanel;
    JButton rlButton;
    JButton saveButton;
    JButton loadButton;

    public MainMenuPanel(BookBuddyApp bookBuddy) {
        this.appFrame = bookBuddy;

        JLabel title = new JLabel();
        title.setText("Welcome to BookBuddy!");
        title.setIcon(new ImageIcon("ezgif.com-resize.gif"));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.TOP);
        title.setFont(largeBoldFont);
        title.setForeground(fontColor);

        this.setBackground(backgroundColor);
        this.add(title);

        setUpButtons();
    }


    // modifies: this
    // effects: creates and initializes buttons
    private void setUpButtons() {
        centrePanel = new JPanel();
        centrePanel.setPreferredSize(new Dimension(450, 250));
        centrePanel.setBackground(backgroundColor);

        rlButton = new JButton("view books I've read");
        initButton(rlButton);

        loadButton = new JButton("load books");
        initButton(loadButton);

        saveButton = new JButton("save books");
        initButton(saveButton);

        this.add(centrePanel, BorderLayout.CENTER);
    }

    // modifies: this
    // effects: sets size, font, and colour of button, then adds to centre panel
    private void initButton(JButton button) {
        button.addActionListener(this);
        button.setPreferredSize(buttonSize);
        button.setFont(medFont);
        button.setForeground(fontColor);
        button.setFocusable(false);

        centrePanel.add(button);
    }

    // modifies: this
    // effects: executes action based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rlButton) {
            appFrame.remove(this);
            readListPanel = new ReadListPanel(appFrame);
            appFrame.add(readListPanel);
            appFrame.revalidate();
            appFrame.repaint();
        }

        if (e.getSource() == loadButton) {
            appFrame.loadBookCollection();
        }

        if (e.getSource() == saveButton) {
            appFrame.saveBookCollection();
        }
    }

}
