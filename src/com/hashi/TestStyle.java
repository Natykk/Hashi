package com.hashi;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;

import com.hashi.style.*;

public class TestStyle extends JFrame {
    private static final int default_width = 1280;
    private static final int default_height = 720;
    public static final StyleWrapper style = new StyleWrapper((Style) new SchoolStyle());

    TestStyle() {
        super("TestStyle");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(default_width, default_height);
        setMinimumSize(new Dimension(default_width, default_height));

        Panel panel = new Panel(style, new FlowLayout(FlowLayout.LEFT), "bg-1.png");
        
        String[] combox_content = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

        Button button = new Button(style, "Jouer");

        button.addActionListener(e -> {
            style.switchStyle((Style) new SummerStyle());
            repaint();
        });

        panel.add(button);
        panel.add(new Label(style, "Jouer"));
        panel.add(new TextField(style));
        panel.add(new ComboBox<String>(style, combox_content));

        add(panel);
        setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new TestStyle());
    }
}