package ui;

import java.awt.*;

// interface for storing colours, fonts, and dimensions that are used throughout program
public interface Constants {
    Dimension buttonSize = new Dimension(400, 50);
    Dimension smallButtonSize = new Dimension(150, 40);
    Dimension textFieldSize = new Dimension(200, 25);

    Font largeBoldFont = new Font(Font.SERIF, Font.BOLD, 45);
    Font medBoldFont = new Font(Font.SERIF, Font.BOLD, 20);
    Font medFont = new Font(Font.SERIF, Font.PLAIN, 20);
    Font smallFont = new Font(Font.SERIF, Font.PLAIN, 15);

    Color fontColor = new Color(94, 80, 63);
    Color backgroundColor = new Color(234, 224, 213);
    Color accentColor = new Color(216,198,178);
}
