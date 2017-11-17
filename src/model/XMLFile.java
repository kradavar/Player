package model;

import controller.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class XMLFile {
    private String fileName;
    private Controller controller;
    private List<Song> songList;

    public XMLFile(String fileName, Controller controller) {
        this.fileName = fileName;
        this.controller = controller;
        songList = controller.getListOfSongs();
    }

    public void writeFile() throws IOException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElement(XMLConst.SONGS);
        document.appendChild(rootElement);


        for (int index = 0; index < songList.size(); index++) {
            Song song = songList.get(index);
            Element songElement = document.createElement(XMLConst.SONG);
            rootElement.appendChild(songElement);

            String element = song.getArtist();
            songElement.setAttribute(XMLConst.ARTIST, element);
            element = song.getAlbum();
            songElement.setAttribute(XMLConst.ALBUM, element);
            element = song.getTitle();
            songElement.setAttribute(XMLConst.TITLE, element);
            element = song.getGenre();
            songElement.setAttribute(XMLConst.GENRE, element);
            element = song.getDirectory();
            songElement.setAttribute(XMLConst.DIRECTORY,element);


            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Properties outFormat = new Properties();
            outFormat.setProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperties(outFormat);
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        }
    }

   /* void readFile() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SongFromFileHandler songFromFileHandler = new SongFromFileHandler(controller);
           // saxParser.parse(new File(fileName), songFromFileHandler);

            List<Song> songList = songFromFileHandler.getSongList();
            controller.updateLists(songList);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } /*catch (IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }*/
}
