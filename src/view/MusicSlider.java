package view;

import model.Song;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class MusicSlider extends JSlider {

    public MusicSlider(){

        this.setMinimum(0);
        //this.setMaximum((int) song.getMicrosecondLength());

    }

    public void changePosition(Clip song){
        this.setValue((int)song.getMicrosecondPosition());
    }
}
