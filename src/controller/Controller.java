package  controller;

import model.MusicLibrary;
import model.Playlist;
import model.Song;
import view.PlayerGUI;

import java.util.List;

public class Controller {

    public MusicLibrary musicLib;
    public Song song;
    public Playlist playlist;
    public PlayerGUI player;

    public void start(){
        musicLib = new MusicLibrary();

        player = new PlayerGUI();
        player.createPlayerGUI(this);

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

    public void forwardSong(){
        Song curSong = musicLib.getCurrentSong();
        Song nextSong = musicLib.getNextSong(curSong);
        player.songInfo.setText(nextSong.getTitle() + " by " + nextSong.getArtist());
        musicLib.setCurrentSong(nextSong);
        musicLib.playSong(nextSong);
    }
    public void stop(){
        musicLib.setCurrentSong(null);
        player.songInfo.setText("No song playing");
        musicLib.stopSong();

    }
    public void play() {
        String songName = (String) player.songList.getSelectedValue();
        Song songToPlay = musicLib.getSong(songName);
        player.songInfo.setText(songName + " by " + songToPlay.getArtist());
        musicLib.setCurrentSong(songToPlay);
        musicLib.playSong(songToPlay);
    }


    public void updateLists(){
        player.songModel.clear();
        player.albumModel.clear();
        player.genreModel.clear();
        player.artistModel.clear();
        player.playlistModel.clear();
        for(String song : player.musicLibrary.getSongTitles()){
            player.songModel.addElement(song);
        }

        for(String album : player.musicLibrary.getAlbums()){
            player.albumModel.addElement(album);
        }
        for(String genre : player.musicLibrary.getGenres()){
            player.genreModel.addElement(genre);
        }
        for(String artist : player.musicLibrary.getArtists()){
            player.artistModel.addElement(artist);
        }
        for(Playlist playlist: player.musicLibrary.getPlaylists()){
            player.playlistModel.addElement(playlist.getTitle());
        }

        player.songList.setModel(player.songModel);
        player.albumList.setModel(player.albumModel);
        player.genreList.setModel(player.genreModel);
        player.artistList.setModel(player.artistModel);
        player.playlistList.setModel(player.playlistModel);
    }

    public List<Song> getListOfSongs() {
        return musicLib.songList;
    }

    public void updateLists(List<Song> songList) {

    }
}