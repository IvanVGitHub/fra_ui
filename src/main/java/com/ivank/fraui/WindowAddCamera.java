package com.ivank.fraui;

import com.ivank.fraui.db.ModelCamera;
import com.ivank.fraui.db.QueryCameras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowAddCamera extends JFrame {
    private static ArrayList<String> listChckBxIsSlctName = new ArrayList<>();

    public WindowAddCamera() {
        super("Список камер");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //получаем список имён всех камер
        ArrayList<String> listCameraName = new ArrayList<>();
        for (ModelCamera event : QueryCameras.getListCamerasALL()) {
            listCameraName.add(String.valueOf(event.camera_name));
        }

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        ArrayList<JCheckBox> checkboxes = new ArrayList<>();

        for(String element : listCameraName) {
            JCheckBox box = new JCheckBox(element);
            checkboxes.add(box);
            panelMain.add(box);
        }

        JButton buttonAddCameras = new JButton("Сохранить");
        buttonAddCameras.setBounds(100,200,100,30);
        buttonAddCameras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int countTEST = 0;
                    listChckBxIsSlctName.clear();
                    for (JCheckBox element : checkboxes) {
                        if (element.isSelected()) {
                            countTEST += 1;

                            listChckBxIsSlctName.add(element.getText());
                        }
                    }
                    JOptionPane.showMessageDialog(
                            null,
                            "Выбрано камер: " + String.valueOf(countTEST) + " " + listChckBxIsSlctName
                    );

                    //сохранение списка выбранных камер в файл настроек
                    AppConfig.getInstance().setCamerasView(listChckBxIsSlctName);
                    AppConfig.saveConfig();

                    //при нажатии на кнопку "Ок" закроется не только диалоговое окно, но и окно выбора камер
                    if (JOptionPane.OK_OPTION == 0) {
                        setVisible(false);
                    }

                    //перерисовываем Content JPanel основном окне
                    Application.windowMain().getContent().setCameraView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(panelMain, BorderLayout.CENTER);
        add(buttonAddCameras, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
