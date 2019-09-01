package com.graphics;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent {

    public Canvas() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
