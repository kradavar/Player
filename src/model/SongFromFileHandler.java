package model;

import controller.Controller;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class SongFromFileHandler extends DefaultHandler {

    private List<Song> songList;
    private Song song;
    private Controller controller;

    SongFromFileHandler(Controller controller){
        songList = new ArrayList<Song>();
        song = null;
        this.controller = controller;
    }

    List<Song> getSongList(){
        return songList;
    }

    public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase(XMLConst.SONG)) {

            String artist = attributes.getValue(XMLConst.ARTIST);
            String album = attributes.getValue(XMLConst.ALBUM);
            String title = attributes.getValue(XMLConst.TITLE);
            String genre = attributes.getValue(XMLConst.GENRE);
            String directory = attributes.getValue(XMLConst.DIRECTORY);

            song = new Song();

            song.setAlbum(album);
            song.setArtist(artist);
            song.setTitle(title);
            song.setGenre(genre);
            song.setDirectory(directory);

            songList.add(song);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

    }
}
