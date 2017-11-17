
package view;

import controller.Controller;
import model.MusicLibrary;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


public class PlayerGUI extends JPanel{

	Controller controller;
	public JList playlistList;
	public JList genreList;
	public JList artistList;
	public JList albumList;
	public JList songList;
	public DefaultListModel playlistModel;
	public DefaultListModel genreModel;
	public DefaultListModel artistModel;
	public DefaultListModel albumModel;
	public DefaultListModel songModel;
	public JTextField songInfo;

	JButton startPauseButton;
	JButton backButton;
	JButton forwardButton;
	JButton stopButton;
	JMenuItem sortSong;
	JMenuItem sortArtist;
	JPopupMenu sortMenu;
	JPanel buttonPanel;
	JSlider musicSlider;
	JLabel albumArtLabel;
	JPanel bottomContainer;
	public MusicLibrary musicLibrary;
	GridBagConstraints constraints;
	
	
	PlayerGUI thisGUI;
	
	public void createPlayerGUI(Controller controller){

		this.controller = controller;

		JFrame playerFrame = new JFrame();
		playerFrame.setPreferredSize(new Dimension(1000, 600));
		playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		musicLibrary = new MusicLibrary();
		
		playlistList = new JList();
		genreList = new JList();
		artistList = new JList();
		albumList = new JList();
		songList = new JList();
		
		songModel = new DefaultListModel();
		genreModel = new DefaultListModel();
		artistModel = new DefaultListModel();
		albumModel = new DefaultListModel();
		playlistModel = new DefaultListModel();
		
		
		songInfo = new JTextField();

		startPauseButton = new JButton("Start/Pause");
		backButton = new JButton("Back");
		forwardButton = new JButton("forwardSong");
		stopButton = new JButton("stop");
		buttonPanel = new JPanel();
		musicSlider = new MusicSlider();
		bottomContainer = new JPanel();

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		thisGUI = this;
		playerFrame.setLayout(new GridBagLayout());

		constraints.gridheight = 4;
		constraints.gridy = 0;
		constraints.gridx = 1;
		playlistList.setBackground(Color.gray);
		playerFrame.add(playlistList, constraints);
		
		constraints.gridy = 4;
		genreList.setBackground(Color.green);
		playerFrame.add(genreList, constraints);
		
		constraints.gridheight = 8;
		constraints.gridx = 2;
		constraints.gridy = 0;
		artistList.setBackground(Color.blue);
		playerFrame.add(artistList, constraints);
		
		constraints.gridx = 3;
		albumList.setBackground(Color.yellow);
		playerFrame.add(albumList, constraints);
		
		constraints.gridx = 4;
		songList.setBackground(Color.orange);
		playerFrame.add(songList, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.weighty = .4;
		songInfo.setText("Test Text");
		songInfo.setBackground(Color.pink);
		playerFrame.add(songInfo, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 3;
		playerFrame.add(bottomContainer, constraints);
		buttonPanel.add(backButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(startPauseButton);
		buttonPanel.add(forwardButton);
		bottomContainer.add(buttonPanel);
		bottomContainer.add(musicSlider);

		JMenuBar Menu = new JMenuBar();
		JMenu FileMenu = new JMenu("File");
		JMenu ViewMenu = new JMenu("View");
		JMenuItem changeColors = new JMenuItem("Change colors");
		JMenuItem changeLanguage = new JMenuItem("Change language");

		JMenuItem addSong = new JMenuItem("Add Song");
		JMenuItem deleteSong = new JMenuItem("Delete Song");
		JMenuItem addPlaylist = new JMenuItem("Add Playlist");
		JMenuItem addSongToPlaylist = new JMenuItem("Add Song to Playlist");
		Menu.add(FileMenu);
		Menu.add(ViewMenu);
		FileMenu.add(addSong);
		FileMenu.add(deleteSong);
		FileMenu.add(addPlaylist);
		ViewMenu.add(changeColors);
		ViewMenu.add(changeLanguage);

		addSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddSongDialog songDialog = new AddSongDialog();

				songDialog.createAddSongDialog(controller);
				//controller.updateLists();
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		deleteSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DeleteSongDialog deleteDialog = new DeleteSongDialog();
				deleteDialog.createDeleteDialog(controller);
				//controller.updateLists(this);
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		addPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				AddPlaylistDialog playlistDialog = new AddPlaylistDialog();
				playlistDialog.createAddPlaylistDialog(controller);
				//controller.updateLists(this);
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		changeColors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeColorDialog colorDialog = new ChangeColorDialog();
				colorDialog.createChangeColorDialog(controller);
							}
		});
		// ДОДЕЛАТЬ ААААААААААААААААА
		addSongToPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		playerFrame.setJMenuBar(Menu);
		playerFrame.revalidate();
		playerFrame.repaint();
		
		sortSong = new JMenuItem("Sort by song title");
		sortArtist = new JMenuItem("Sort by artist name");
		sortMenu = new JPopupMenu();
		sortMenu.add(sortSong);
		sortMenu.add(sortArtist);
		
		startPauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				controller.play();
			}
		});
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.stop();
			}
		});
		forwardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.forwardSong();
			}
		});
		songList.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(SwingUtilities.isRightMouseButton(e)){
					sortMenu.show(songList, e.getX(), e.getY());
				}
			}
		});
		sortSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				HashSet<String> songTitles = musicLibrary.getSongTitles();
				String[] tempArray = new String[songTitles.size()];
				int i = 0;
				for(String entry : songTitles){
					tempArray[i] = entry;
					i++;
				}
				Arrays.sort(tempArray);				
				songModel.clear();
				for(String song : tempArray){
					songModel.addElement(song);
				}
			}	
		});
		sortArtist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HashSet<String> artists = musicLibrary.getArtists();
				String[] tempArray = new String[artists.size()];
				int i = 0;
				for(String entry : artists){
					tempArray[i] = entry;
					i++;
				}
				Arrays.sort(tempArray);
				songModel.clear();
				for(String entry : tempArray){
					for(Song tempSong: musicLibrary.getSongLibrary().values()){
						if(tempSong.getArtist() == entry){
							songModel.addElement(tempSong.getTitle());
						}
					}
				}
			}
		});

		playerFrame.pack();
		playerFrame.setVisible(true);
	}

}