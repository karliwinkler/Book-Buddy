package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements Constants, ActionListener {
    BookBuddyApp appFrame;
    ReadListPanel readListPanel;
    JPanel centrePanel;
    JButton rlButton;
    JButton wtrButton;
    JButton favButton;
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

    public void setUpButtons() {
        centrePanel = new JPanel();
        centrePanel.setPreferredSize(new Dimension(450, 250));
        centrePanel.setBackground(backgroundColor);

        rlButton = new JButton("view books I've read");
        initButton(rlButton, buttonSize);
        wtrButton = new JButton("view books I want to read");
        initButton(wtrButton, buttonSize);
        favButton = new JButton("view my favourite books");
        initButton(favButton, buttonSize);


        loadButton = new JButton("load books");
        initButton(loadButton, halfButtonSize);
        saveButton = new JButton("save books");
        initButton(saveButton, halfButtonSize);
        centrePanel.add(Box.createVerticalStrut(100));

        this.add(centrePanel, BorderLayout.CENTER);
    }

    public void initButton(JButton button, Dimension size) {
        button.addActionListener(this);
        button.setPreferredSize(size);
        button.setFont(medFont);
        button.setForeground(fontColor);
        button.setFocusable(false);

        centrePanel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rlButton) {
            appFrame.remove(this);
            readListPanel = new ReadListPanel(appFrame.getBookCollection());
            appFrame.add(readListPanel);
            appFrame.revalidate();
            appFrame.repaint();
        }

        if (e.getSource() == wtrButton) {
            //
        }

        if (e.getSource() == favButton) {
            //
        }

        if (e.getSource() == loadButton) {
            appFrame.loadBookCollection();
        }

        if (e.getSource() == saveButton) {
            appFrame.saveBookCollection();
        }
    }

}
