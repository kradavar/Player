package model;

import controller.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenXMLFileListener /*implements ActionListener */{
    private Controller controller;
    private JFileChooser fileChooser;

    public OpenXMLFileListener(Controller controller){
        this.controller = controller;
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter nameFilter = new FileNameExtensionFilter("XML Формат",
                "xml");
        fileChooser.setFileFilter(nameFilter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Открыть файл");
    }

    //@Override
    /*public void actionPerformed(ActionEvent e) {
        if (fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
            new XMLFile (fileChooser.getSelectedFile().getPath(),controller).readFile();
        }
    }*/
}

