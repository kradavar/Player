package  controller;

import model.MusicLibrary;
import model.Playlist;
import model.Song;
import view.PlayerGUI;

import javax.swing.*;
import java.awt.*;

public class Controller {

    public MusicLibrary musicLib;
    public Song song;
    public Playlist playlist;
    public PlayerGUI player;

    public  Controller(){ }

    public void start(){
        musicLib = new MusicLibrary();

        JFrame playerFrame = new JFrame();
        playerFrame.setPreferredSize(new Dimension(1000, 600));
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player = new PlayerGUI(this);
        player.createPlayer(playerFrame);
        player.createMenu(playerFrame, this);
        playerFrame.pack();
        playerFrame.setVisible(true);

    }

    public void addSong (String genre, String artist, String album, String title, String directory) {
        Song newSong = new Song(genre, artist, album, title, directory);
        musicLib.songLibraryMap.put(title, newSong);
        if (musicLib.artists.contains(artist) == false) {
            musicLib.artists.add(artist);
        }
        if (musicLib.albums.contains(album) == false) {
            musicLib.albums.add(album);
        }
        if (musicLib.genres.contains(genre) == false) {
            musicLib.genres.add(genre);
        }
        if (musicLib.songTitles.contains(title) == false) {
            musicLib.songTitles.add(title);
        }
    }

}