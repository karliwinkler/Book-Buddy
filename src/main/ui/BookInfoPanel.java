package ui;

import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// side panel with info on specific book and option to rate and review book
public class BookInfoPanel extends JPanel implements PanelStyle, ActionListener {
    Book book;
    JLabel title;
    JLabel author;
    JLabel rating;
    JLabel review;
    JButton rateButton;
    JButton reviewButton;

    public BookInfoPanel(Book book) {
        this.book = book;
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(accentColor);
        setUpLabels();
        setUpButtons();

    }

    // modifies: this
    // effects: creates and adds labels to panel
    private void setUpLabels() {
        title = new JLabel(book.getTitle());
        title.setFont(medBoldFont);
        title.setForeground(fontColor);
        this.add(title);

        author = new JLabel(book.getAuthor());
        author.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
        author.setForeground(fontColor);
        this.add(author);

        rating = new JLabel("My rating: " + book.getRating() + "/5 stars");
        rating.setFont(smallFont);
        rating.setForeground(fontColor);
        this.add(rating);

        review = new JLabel("My review: " + book.getReview());
        review.setFont(smallFont);
        review.setForeground(fontColor);
        this.add(review);

    }

    // modifies: this
    // effects: creates and adds buttons to panel
    private void setUpButtons() {
        rateButton = new JButton("add rating");
        rateButton.addActionListener(this);
        rateButton.setFont(smallFont);
        rateButton.setForeground(fontColor);
        rateButton.setPreferredSize(smallButtonSize);
        this.add(rateButton);

        reviewButton = new JButton("add review");
        reviewButton.addActionListener(this);
        reviewButton.setFont(smallFont);
        reviewButton.setForeground(fontColor);
        reviewButton.setPreferredSize(smallButtonSize);
        this.add(reviewButton);
    }

    // modifies: this
    // effects: removes and reloads labels to reflect changes to book info
    private void update() {
        this.removeAll();
        setUpLabels();
        setUpButtons();
        this.revalidate();
        this.repaint();
    }

    // modifies: this
    // effects: executes action based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rateButton) {
            String[] ratings = {"1 star - terrible", "2 stars - not very good", "3 stars - ok",
                    "4 stars - pretty good!", "5 stars - amazing!"};
            JComboBox comboBox = new JComboBox(ratings);
            comboBox.setSelectedIndex(4);
            JOptionPane.showOptionDialog(null, comboBox, "Choose a rating",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 1);

            int rating = comboBox.getSelectedIndex() + 1;
            book.rateBook(rating);
            update();
        }

        if (e.getSource() == reviewButton) {
            String review = JOptionPane.showInputDialog(null, "Please enter your review",
                    "Review Book", JOptionPane.PLAIN_MESSAGE);

            book.reviewBook(review);
            update();
        }
    }
}
