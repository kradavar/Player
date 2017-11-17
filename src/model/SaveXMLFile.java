package model;

import controller.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveXMLFile implements ActionListener {
    private JFileChooser fileChooser;
    private Controller controller;

    public SaveXMLFile(Controller controller) {
        fileChooser = new JFileChooser();
        this.controller = controller;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Формат", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Сохранить файл");
    }

    public void actionPerformed(ActionEvent e) {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                new XMLFile(fileChooser.getSelectedFile().getPath(), controller).writeFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (TransformerException exception) {
                exception.printStackTrace();
            } catch (ParserConfigurationException exception) {
                exception.printStackTrace();
            }
        }
    }
}
