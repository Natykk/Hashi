package com.hashi;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import com.hashi.style.*;

public class TestStyle extends JFrame {
    private static final int default_width = 450;
    private static final int default_height = 500;
    public static final StyleWrapper style = new StyleWrapper((Style) new PaperStyle());

    TestStyle() {
        super("TestStyle");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(default_width, default_height);
        setMinimumSize(new Dimension(default_width, default_height));

        Panel panel = new Panel(style, new FlowLayout(FlowLayout.LEFT));
        
        String[] combox_content = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

        panel.add(new Button(style, "Jouer"));
        panel.add(new Label(style, "Jouer"));
        panel.add(new TextField(style));
        panel.add(new ComboBox<String>(style, combox_content));

        add(panel);
        setVisible(true);
    }

    public static void main(String args[]) {
        TestStyle frame = new TestStyle();
    }
}