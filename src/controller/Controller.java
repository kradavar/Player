/*package controller;

import model.Playlist;
import model.Song;
import view.MainWindow;
import view.PanelPlaylist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private MainWindow player;
    private PanelPlaylist listsPanel;
    private boolean isOpen;

    public void start() {
        player = new MainWindow(this);
    }

    public Song openNewSong(String s){
        try {
            File file = new File(s);
            Song newSong = new Song();
            newSong.openNewSong(file);
            isOpen = true;
             return newSong;

        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playMusic(Song song)  {
        if (isOpen) {
            song.play(true);
        }
        // else ShowMessage
    }

    public void playListOfSongs(Playlist playlist){
        playlist.isCurrent = true;

        for (Song currentSong : playlist) {
            currentSong.play(true);
            listsPanel.currentSong = currentSong;
            listsPanel.changeSelectedSong();
        }

    }

    public void setCurrentPlaylist(Playlist playlist){
        if(!playlist.isCurrent) {
            listsPanel.currentPlaylist = playlist;
            playlist.isCurrent = true;
        }
    }



}*/
