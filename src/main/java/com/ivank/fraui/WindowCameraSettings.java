package com.ivank.fraui;

import javax.swing.*;
import java.awt.*;

public class WindowCameraSettings extends JFrame {
    public WindowCameraSettings() {
        super("Настройки камеры");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(300, 300));

        add(panelMain);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}