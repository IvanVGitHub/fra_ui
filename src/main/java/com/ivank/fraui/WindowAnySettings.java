package com.ivank.fraui;

import com.ivank.fraui.settings.AppConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowAnySettings extends JFrame {
    private JPanel panelMain;
    private JButton buttonApply;
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;

    public WindowAnySettings() {
        super("Общие настройки");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        textField.setText(String.valueOf(AppConfig.getInstance().getLabelSize().width));
        textField1.setText(String.valueOf(AppConfig.getInstance().getLabelSize().height));
        textField2.setText(String.valueOf(AppConfig.getInstance().getEventLimit()));

        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        //сохранение настроек подключения к БД в файл настроек
                        AppConfig.getInstance().setEventLimit(Integer.parseInt(textField.getText()));
                        AppConfig.getInstance().setLabelSize()(Integer.parseInt(textField1.getText()));
                        AppConfig.getInstance().setEventLimit(Integer.parseInt(textField2.getText()));
                        AppConfig.saveConfig();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //диалоговое окно не будет выводиться из-за преднамеренной ошибки в коде (вместо ERROR
                        //должен быть ERROR_MESSAGE), зато теперь доступен повторный ввод в textField
                        JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR);
                    }
                    //перерисовываем Content (JPanel) в основном окне (WindowMain)
                    Application.windowMain().getContent().setCameraView();

                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setContentPane(panelMain);
        setVisible(true);
        pack();//окно создаётся по размерам внутренних элементов, а не [0;0] px
        setLocationRelativeTo(null);
    }
}
