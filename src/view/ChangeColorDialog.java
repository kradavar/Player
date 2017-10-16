package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeColorDialog extends JDialog {

    JRadioButton pinkArtist;
    JRadioButton grayArtist;
    JRadioButton yellowArtist;
    JRadioButton blueArtist;

    ButtonGroup colorArtist;

    JRadioButton pinkAlbum;
    JRadioButton grayAlbum;
    JRadioButton yellowAlbum;
    JRadioButton blueAlbum;

    ButtonGroup colorAlbum;

    JRadioButton pinkSong;
    JRadioButton graySong;
    JRadioButton yellowSong;
    JRadioButton blueSong;

    ButtonGroup colorSong;

    JRadioButton pinkPlaylist;
    JRadioButton grayPlaylist;
    JRadioButton yellowPlaylist;
    JRadioButton bluePlaylist;

    ButtonGroup colorPlaylist;

    JRadioButton pinkGenre;
    JRadioButton grayGenre;
    JRadioButton yellowGenre;
    JRadioButton blueGenre;

    ButtonGroup colorGenre;

    JLabel artistlabel, albumLabel, playLabel, songLabel, genreLabel;

    JButton buttonOK, buttonCANCEL;

    JPanel artistColor = new JPanel();
    JPanel songColor = new JPanel();
    JPanel playlistColor = new JPanel();
    JPanel albumColor = new JPanel();
    JPanel genreColor = new JPanel();
    JPanel buttonPanel = new JPanel();


    public ChangeColorDialog (PlayerGUI player){
        super();
        setModal(true);

        this.setLayout(new GridLayout(11,1));

        pinkSong = new JRadioButton("PINK");
        pinkAlbum = new JRadioButton("PINK");
        pinkArtist = new JRadioButton("PINK");
        pinkGenre = new JRadioButton("PINK");
        pinkPlaylist = new JRadioButton("PINK");

        grayAlbum = new JRadioButton("GRAY");
        grayArtist = new JRadioButton("GRAY");
        grayGenre = new JRadioButton("GRAY");
        graySong = new JRadioButton("GRAY");
        grayPlaylist = new JRadioButton("GRAY");

        yellowAlbum = new JRadioButton("YELLOW");
        yellowArtist = new JRadioButton("YELLOW");
        yellowGenre = new JRadioButton("YELLOW");
        yellowPlaylist = new JRadioButton("YELLOW");
        yellowSong = new JRadioButton("YELLOW");

        blueAlbum = new JRadioButton("BLUE");
        blueArtist = new JRadioButton("BLUE");
        blueGenre = new JRadioButton("BLUE");
        bluePlaylist = new JRadioButton("BLUE");
        blueSong = new JRadioButton("BLUE");

        colorSong = new ButtonGroup();
        colorAlbum = new ButtonGroup();
        colorArtist = new ButtonGroup();
        colorGenre = new ButtonGroup();
        colorPlaylist = new ButtonGroup();

        colorSong.add(pinkSong);
        colorSong.add(graySong);
        colorSong.add(yellowSong);
        colorSong.add(blueSong);

        colorPlaylist.add(pinkPlaylist);
        colorPlaylist.add(grayPlaylist);
        colorPlaylist.add(yellowPlaylist);
        colorPlaylist.add(bluePlaylist);

        colorGenre.add(pinkGenre);
        colorGenre.add(grayGenre);
        colorGenre.add(yellowGenre);
        colorGenre.add(blueGenre);

        colorArtist.add(pinkArtist);
        colorArtist.add(grayArtist);
        colorArtist.add(yellowArtist);
        colorArtist.add(blueArtist);

        colorAlbum.add(pinkAlbum);
        colorAlbum.add(grayAlbum);
        colorAlbum.add(yellowAlbum);
        colorAlbum.add(blueAlbum);

        artistlabel = new JLabel("Change artist BG color");
        albumLabel = new JLabel("Change album BG color");
        genreLabel = new JLabel("Change genre BG color");
        songLabel = new JLabel("Change song BG color");
        playLabel = new JLabel("Change playlist BG color");

        this.add(artistlabel);
        artistColor.add(pinkArtist);
        artistColor.add(grayArtist);
        artistColor.add(yellowArtist);
        artistColor.add(blueArtist);
        this.add(artistColor);

        this.add(songLabel);
        songColor.add(pinkSong);
        songColor.add(graySong);
        songColor.add(yellowSong);
        songColor.add(blueSong);
        this.add(songColor);

        this.add(albumLabel);
        albumColor.add(pinkAlbum);
        albumColor.add(grayAlbum);
        albumColor.add(yellowAlbum);
        albumColor.add(blueAlbum);
        this.add(albumColor);

        this.add(playLabel);
        playlistColor.add(pinkPlaylist);
        playlistColor.add(grayPlaylist);
        playlistColor.add(yellowPlaylist);
        playlistColor.add(bluePlaylist);
        this.add(playlistColor);

        this.add(genreLabel);
        genreColor.add(pinkGenre);
        genreColor.add(grayGenre);
        genreColor.add(yellowGenre);
        genreColor.add(blueGenre);
        this.add(genreColor);

        buttonOK = new JButton("OK");
        buttonCANCEL = new JButton("Cancel");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCANCEL);
        this.add(buttonPanel);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pinkArtist.isSelected()){

                    player.artistList.setBackground(Color.pink);
                }
                     else if (grayArtist.isSelected()){
                            player.artistList.setBackground(Color.gray);
                        }
                        else if (yellowArtist.isSelected()){
                                 player.artistList.setBackground(Color.yellow);
                            }
                                else if (blueArtist.isSelected()){
                                        player.artistList.setBackground(Color.blue);
                                    }

                if(pinkSong.isSelected()){

                    player.songList.setBackground(Color.pink);
                }
                else if (graySong.isSelected()){
                    player.songList.setBackground(Color.gray);
                }
                else if (yellowSong.isSelected()){
                    player.songList.setBackground(Color.yellow);
                }
                else if (blueSong.isSelected()){
                    player.songList.setBackground(Color.blue);
                }

                if(pinkAlbum.isSelected()){

                    player.albumList.setBackground(Color.pink);
                }
                else if (grayAlbum.isSelected()){
                    player.albumList.setBackground(Color.gray);
                }
                else if (yellowAlbum.isSelected()){
                    player.albumList.setBackground(Color.yellow);
                }
                else if (blueAlbum.isSelected()){
                    player.albumList.setBackground(Color.blue);
                }

                if(pinkGenre.isSelected()){

                    player.genreList.setBackground(Color.pink);
                }
                else if (grayGenre.isSelected()){
                    player.genreList.setBackground(Color.gray);
                }
                else if (yellowGenre.isSelected()){
                    player.genreList.setBackground(Color.yellow);
                }
                else if (blueGenre.isSelected()){
                    player.genreList.setBackground(Color.blue);
                }

                if(pinkPlaylist.isSelected()){

                    player.playlistList.setBackground(Color.pink);
                }
                else if (grayPlaylist.isSelected()){
                    player.playlistList.setBackground(Color.gray);
                }
                else if (yellowPlaylist.isSelected()){
                    player.playlistList.setBackground(Color.yellow);
                }
                else if (bluePlaylist.isSelected()){
                    player.playlistList.setBackground(Color.blue);
                }
            }
        });


        pack();
    }


}
