
package view;

import controller.Controller;
import model.MusicLibrary;
import model.Playlist;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


public class PlayerGUI extends JPanel{

	JList playlistList;
	JList genreList;
	JList artistList;
	JList albumList;
	JList songList;
	DefaultListModel playlistModel;
	DefaultListModel genreModel;
	DefaultListModel artistModel;
	DefaultListModel albumModel;
	DefaultListModel songModel;
	JTextField songInfo;

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
	MusicLibrary musicLibrary;
	GridBagConstraints constraints;
	
	
	PlayerGUI thisGUI;
	
	public PlayerGUI(Controller controller){
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
		forwardButton = new JButton("Forward");
		stopButton = new JButton("Stop");
		buttonPanel = new JPanel();
		musicSlider = new MusicSlider();
		bottomContainer = new JPanel();

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		thisGUI = this;
		this.setLayout(new GridBagLayout());

		constraints.gridheight = 4;
		constraints.gridy = 0;
		constraints.gridx = 1;
		playlistList.setBackground(Color.gray);
		this.add(playlistList, constraints);
		
		constraints.gridy = 4;
		genreList.setBackground(Color.green);
		this.add(genreList, constraints);
		
		constraints.gridheight = 8;
		constraints.gridx = 2;
		constraints.gridy = 0;
		artistList.setBackground(Color.blue);
		this.add(artistList, constraints);
		
		constraints.gridx = 3;
		albumList.setBackground(Color.yellow);
		this.add(albumList, constraints);
		
		constraints.gridx = 4;
		songList.setBackground(Color.orange);
		this.add(songList, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.weighty = .4;
		songInfo.setText("Test Text");
		songInfo.setBackground(Color.pink);
		this.add(songInfo, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 3;
		this.add(bottomContainer, constraints);
		buttonPanel.add(backButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(startPauseButton);
		buttonPanel.add(forwardButton);
		bottomContainer.add(buttonPanel);
		bottomContainer.add(musicSlider);


		try {
			musicLibrary = MusicLibrary.parseLibrary("Library.txt");
			updateLists();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sortSong = new JMenuItem("Sort by song title");
		sortArtist = new JMenuItem("Sort by artist name");
		sortMenu = new JPopupMenu();
		sortMenu.add(sortSong);
		sortMenu.add(sortArtist);
		
		startPauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Play();
			}
		});
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Stop();
			}
		});
		forwardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Forward();
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
	}

	public void Forward(){
		Song curSong = musicLibrary.getCurrentSong();
		Song nextSong = musicLibrary.getNextSong(curSong);
		songInfo.setText(nextSong.getTitle() + " by " + nextSong.getArtist());
		musicLibrary.setCurrentSong(nextSong);
		musicLibrary.playSong(nextSong);
	}
	public void Stop(){
		musicLibrary.setCurrentSong(null);
		songInfo.setText("No song playing");
		musicLibrary.stopSong();

	}
	public void Play(){
		String songName = (String)songList.getSelectedValue();
		Song songToPlay = musicLibrary.getSong(songName);
		songInfo.setText(songName + " by " + songToPlay.getArtist());
		musicLibrary.setCurrentSong(songToPlay);
		musicLibrary.playSong(songToPlay);
	}
	public void createPlayer(JFrame TitanFrame){
		TitanFrame.getContentPane().add(this);
	}

		public void createMenu(JFrame mainFrame, Controller controller){
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
				updateLists();
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
				updateLists();
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
				updateLists();
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
				updateLists();

			}
		});
			// ДОДЕЛАТЬ ААААААААААААААААА
		addSongToPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		
		mainFrame.setJMenuBar(Menu);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	public void updateLists(){
		songModel.clear();
		albumModel.clear();
		genreModel.clear();
		artistModel.clear();
		playlistModel.clear();
		for(String song : musicLibrary.getSongTitles()){
			songModel.addElement(song);
		}

		for(String album : musicLibrary.getAlbums()){
			albumModel.addElement(album);
		}
		for(String genre : musicLibrary.getGenres()){
			genreModel.addElement(genre);
		}
		for(String artist : musicLibrary.getArtists()){
			artistModel.addElement(artist);
		}
		for(Playlist playlist: musicLibrary.getPlaylists()){
			playlistModel.addElement(playlist.getTitle());
		}
		
		songList.setModel(songModel);
		albumList.setModel(albumModel);
		genreList.setModel(genreModel);
		artistList.setModel(artistModel);
		playlistList.setModel(playlistModel);
	}
}