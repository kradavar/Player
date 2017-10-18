package view;

import controller.Controller;
import model.MusicLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSongDialog extends JDialog{
	MusicLibrary musicLibrary;
	JLabel instructions;
	JLabel deleteSongLabel;
	JTextField deleteSongField;
	JButton deleteSong;
	JButton cancel;
	JPanel buttonPanel;
	String deleteSongTitle;


    public void createDeleteDialog(Controller controller) {
		musicLibrary = controller.musicLib;
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		instructions = new JLabel("Type in the title of a song to delete it.");
		deleteSongLabel = new JLabel("Title: ");
		deleteSongField = new JTextField();
		buttonPanel = new JPanel();
		deleteSongField.setPreferredSize(new Dimension(500, 25));
		deleteSong = new JButton("Delete");
		cancel = new JButton("Cancel");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		this.add(instructions, constraints);
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		this.add(deleteSongLabel, constraints);
		constraints.gridx = 1;
		this.add(deleteSongField, constraints);
		constraints.gridy = 2;
		constraints.gridx = 0;
		buttonPanel.add(deleteSong);
		buttonPanel.add(cancel);
		this.add(buttonPanel, constraints);
		pack();

		deleteSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(deleteSongField.getText().length() > 0){
					musicLibrary.removeSong(deleteSongField.getText());
				}
				setVisible(false);
				dispose();
			}

		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
    }
}
