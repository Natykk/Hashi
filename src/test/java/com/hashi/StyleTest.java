package com.hashi;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.style.*;

public class StyleTest {
    @BeforeEach
    public void beforeMessage(TestInfo testInfo) {
        System.out.println(
                "===================== Start testing : " + testInfo.getDisplayName() + " =====================");
    }

    @AfterEach
    public void afterMessage(TestInfo testInfo) {
        System.out.println(
                "===================== Test ended OK : " + testInfo.getDisplayName() + " =====================");
    }

    class StyleTestClass extends JFrame {
        private static final int default_width = 1280;
        private static final int default_height = 720;

        StyleTestClass() {
            super("StyleTestClass");

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setSize(default_width, default_height);
            setMinimumSize(new Dimension(default_width, default_height));

            Panel panel = new Panel(new FlowLayout(FlowLayout.LEFT), "bg-principal.png");

            String[] combox_content = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

            Button button = new Button("test");

            button.addActionListener(e -> {
                StyleManager.setStyle((Style) new SummerStyle());
                Language.getInstance().setLanguage("en");
                repaint();
            });

            panel.add(button);
            panel.add(new Label("test").setFontSize(50));
            panel.add(new TextField(10).setFontSize(30));
            panel.add(new ComboBox<String>(combox_content));

            add(panel);
            setVisible(true);
        }
    }

    @Test
    public void style() throws InvocationTargetException, InterruptedException {
        StyleTestClass styleTestClass = new StyleTestClass();

        while (styleTestClass.isVisible()) {
            Thread.sleep(1000);
        }
    }
}
