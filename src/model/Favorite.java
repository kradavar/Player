package model;

import sun.plugin2.message.ShowDocumentMessage;

public class Favorite {
    Playlist favoriteSongs;


    public void getFavoriteSongs(){}

    public void addToFavorites(Song song){
        if (!song.isFavorite) {
            favoriteSongs.addToList(song);
        }
        // else �������� ��������� � ���, ��� ����� ��� ��� ����
    }

    public void removeFromFavorite(Song song){
        if (song.isFavorite){
            favoriteSongs.removeFromList(song);
        }
    }
}
