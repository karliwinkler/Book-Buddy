package ui;

import model.Book;
import model.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static model.Genre.*;

// panel showing list of read books, option to add books, and book info
public class ReadListPanel extends JPanel implements PanelStyle, ActionListener {
    private BookBuddyApp appFrame;
    private ListPanel listPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;

    private JButton backButton;
    private JButton addBookButton;
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox genreOptions;

    public ReadListPanel(BookBuddyApp appFrame) {
        this.setLayout(new BorderLayout(20, 20));
        this.appFrame = appFrame;
        this.setBackground(backgroundColor);

        topPanel = new JPanel();
        listPanel = new ListPanel(this);
        bottomPanel = new JPanel();

        setUpFields();
        setUpButtons();

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(backgroundColor);
        JLabel title = new JLabel("My read books");
        title.setForeground(fontColor);
        title.setFont(largeBoldFont);
        topPanel.add(Box.createHorizontalStrut(160));
        topPanel.add(title);

        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setPreferredSize(new Dimension(100, 100));

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(listPanel, BorderLayout.CENTER);
        this.add(makeLeftPanel(), BorderLayout.LINE_START);
        this.add(makeRightPanel(), BorderLayout.LINE_END);
        this.add(bottomPanel, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    // modifies: this
    // effects: creates and initializes textfields and combo box
    private void setUpFields() {
        titleField = new JTextField();
        titleField.addActionListener(this);
        initComponent(titleField, "Title:");

        authorField = new JTextField();
        authorField.addActionListener(this);
        initComponent(authorField, "Author:");

        String[] genres = {"classic", "fantasy", "mystery", "non-fiction", "romance", "contemporary", "sci-fi",};
        genreOptions = new JComboBox(genres);
        genreOptions.addActionListener(this);
        initComponent(genreOptions, "Genre: ");
    }

    private void initComponent(JComponent component, String labelStr) {
        component.setFont(smallFont);
        component.setForeground(fontColor);
        component.setPreferredSize(textFieldSize);

        JLabel label = new JLabel(labelStr);
        label.setFont(smallFont);
        label.setForeground(fontColor);

        bottomPanel.add(label);
        bottomPanel.add(component);
    }

    // modifies: this
    // effects: creates plain left panel
    private JPanel makeLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(backgroundColor);
        leftPanel.setPreferredSize(new Dimension(50, 200));
        return leftPanel;
    }

    // modifies: this
    // effects: creates right panel with "no books selected"
    private JPanel makeRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(accentColor);
        rightPanel.setPreferredSize(new Dimension(200, 200));

        JLabel label = new JLabel("No books selected.");
        label.setFont(medBoldFont);
        label.setForeground(fontColor);
        rightPanel.add(label);
        return rightPanel;
    }

    // modifies: this
    // effects: creates and initializes buttons
    private void setUpButtons() {
        backButton = new JButton(new ImageIcon("brown arrow.png"));
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(75, 50));
        backButton.setBorder(null);
        topPanel.add(backButton);

        addBookButton = new JButton("add new book");
        addBookButton.addActionListener(this);
        addBookButton.setFont(smallFont);
        addBookButton.setForeground(fontColor);
        addBookButton.setPreferredSize(smallButtonSize);
        addBookButton.setFocusable(false);
        bottomPanel.add(addBookButton);

    }

    // effects: helper method that returns correct genre based on given string
    private Genre selectGenre(String genreStr) {
        switch (genreStr) {
            case "classic":
                return classic;
            case "fantasy":
                return fantasy;
            case "mystery":
                return mystery;
            case "romance":
                return romance;
            case "non-fiction":
                return nonfiction;
            case "sci-fi":
                return scifi;
            case "contemporary":
                return contemporary;
        }
        return none;
    }

    // modifies: this
    // effects: updates JList model so that user changes are reflected in GUI
    private void updateListPanel() {
        listPanel.updateListModel();
    }

    // modifies: this
    // effects: executes action based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String titleStr = titleField.getText();
        String authorStr = authorField.getText();
        String genreStr = (String) genreOptions.getSelectedItem();

        if (e.getSource() == addBookButton) {
            if (!titleStr.equals("") && !authorStr.equals("")) {
                Book book = new Book(titleStr, authorStr, selectGenre(genreStr));
                appFrame.getBookCollection().readBook(book);
                titleField.setText("");
                authorField.setText("");
                updateListPanel();
            }
        }

        if (e.getSource() == backButton) {
            appFrame.remove(this);
            appFrame.add(new MainMenuPanel(appFrame));
            appFrame.revalidate();
            appFrame.repaint();
        }
    }

    // getters
    public List<Book> getReadBooks() {
        return appFrame.getBookCollection().getReadBooks();
    }
}
