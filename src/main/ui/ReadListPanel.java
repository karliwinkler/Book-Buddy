package ui;

import model.Book;
import model.BookCollection;
import model.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static model.Genre.*;

public class ReadListPanel extends JPanel implements Constants, ActionListener {
    private BookCollection bookCollection;
    private ListPanel listPanel;
    private JPanel bottomPanel;

    private JButton backButton;
    private JButton addBookButton;
    private JButton statsButton;
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox genreOptions;

    public ReadListPanel(BookCollection bookCollection) {
        this.setLayout(new BorderLayout(20, 20));
        this.bookCollection = bookCollection;

        this.setBackground(backgroundColor);
        JLabel title = new JLabel("My read books", SwingConstants.CENTER);
        title.setForeground(fontColor);
        title.setFont(largeBoldFont);
        this.add(title, BorderLayout.PAGE_START);

        listPanel = new ListPanel(this);
        this.add(listPanel, BorderLayout.CENTER);

        setUpFields();
        setUpButtons();

        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setPreferredSize(new Dimension(100, 100));

        this.add(makeLeftPanel(), BorderLayout.LINE_START);
        this.add(makeRightPanel(), BorderLayout.LINE_END);
        this.add(bottomPanel, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    public void setUpFields() {
        bottomPanel = new JPanel();

        titleField = new JTextField();
        titleField.addActionListener(this);
        setUpComponent(titleField, "Title:");

        authorField = new JTextField();
        authorField.addActionListener(this);
        setUpComponent(authorField, "Author:");

        String[] genres = {"classic", "fantasy", "mystery", "non-fiction", "romance", "contemporary", "sci-fi",};
        genreOptions = new JComboBox(genres);
        genreOptions.addActionListener(this);
        setUpComponent(genreOptions, "Genre: ");
    }

    public void setUpComponent(JComponent component, String labelStr) {
        component.setFont(smallFont);
        component.setForeground(fontColor);
        component.setPreferredSize(textFieldSize);

        JLabel label = new JLabel(labelStr);
        label.setFont(smallFont);
        label.setForeground(fontColor);

        bottomPanel.add(label);
        bottomPanel.add(component);
    }

    public JPanel makeLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(backgroundColor);
        leftPanel.setPreferredSize(new Dimension(50, 200));
        return leftPanel;
    }

    public JPanel makeRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(accentColor);
        rightPanel.setPreferredSize(new Dimension(200, 200));

        JLabel label = new JLabel("No books selected.");
        label.setFont(medBoldFont);
        label.setForeground(fontColor);
        rightPanel.add(label);
        return rightPanel;
    }

    public void setUpButtons() {
        backButton = new JButton("back");
        backButton.addActionListener(this);
        backButton.setFont(smallFont);
        backButton.setForeground(fontColor);
        backButton.setPreferredSize(smallButtonSize);

        addBookButton = new JButton("add new book");
        addBookButton.addActionListener(this);
        addBookButton.setFont(smallFont);
        addBookButton.setForeground(fontColor);
        addBookButton.setPreferredSize(smallButtonSize);
        bottomPanel.add(addBookButton);

        statsButton = new JButton("my reading stats");
        statsButton.addActionListener(this);
        statsButton.setFont(smallFont);
        statsButton.setForeground(fontColor);
        statsButton.setPreferredSize(smallButtonSize);
        bottomPanel.add(statsButton);

    }

    public Genre selectGenre(String genreStr) {
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

    public void updateListPanel() {
        listPanel.updateListModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titleStr = titleField.getText();
        String authorStr = authorField.getText();
        String genreStr = (String) genreOptions.getSelectedItem();

        if (e.getSource() == addBookButton) {
            if (!titleStr.equals("") && !authorStr.equals("")) {
                Book book = new Book(titleStr, authorStr, selectGenre(genreStr));
                bookCollection.readBook(book);
                updateListPanel();
            }
        }

        if (e.getSource() == statsButton) {
            //
        }
    }

    // getters
    public List<Book> getReadBooks() {
        return bookCollection.getReadBooks();
    }
}
