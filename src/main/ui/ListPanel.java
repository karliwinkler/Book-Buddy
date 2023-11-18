package ui;

import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListPanel extends JPanel implements Constants {
    private ReadListPanel readListPanel;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public ListPanel(ReadListPanel readListPanel) {
        this.readListPanel = readListPanel;
        this.setBackground(Color.white);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        updateListModel();
    }

    public void addBooksToListModel(List<Book> books) {
        for (Book b : books) {
            addBookToListModel(b);
        }
    }

    public void addBookToListModel(Book b) {
        String string = b.getTitle() + " by " + b.getAuthor() + " - genre: " + b.getGenre();
        listModel.addElement(string);
    }

    public void updateListModel() {
        listModel.clear();
        List<Book> readBooks = readListPanel.getReadBooks();
        addBooksToListModel(readBooks);
        this.remove(list);
        list = new JList<>(listModel);

        list.setFont(smallFont);
        list.setForeground(fontColor);
        list.setSelectionBackground(fontColor);
        list.setSelectionForeground(backgroundColor);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doubleClickList(e);
            }
        });

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(list);
    }

    public void doubleClickList(MouseEvent e) {
        JList list = (JList) e.getSource();
        if (e.getClickCount() == 2) {
            int index = list.locationToIndex(e.getPoint());
            List<Book> books = readListPanel.getReadBooks();

            BorderLayout layout = (BorderLayout) readListPanel.getLayout();
            readListPanel.remove(layout.getLayoutComponent(BorderLayout.LINE_END));

            readListPanel.add(new BookInfoPanel(books.get(index)), BorderLayout.LINE_END);
            readListPanel.revalidate();
            readListPanel.repaint();
        }
    }

}
