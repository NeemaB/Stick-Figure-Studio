package Util;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import drawableObjects.StickFigure;
import processing.core.PApplet;

/**
 * Created by neema boutorabi on 2016-08-24.
 */
public class Drawer {

    //PApplet used to draw objects onto the screen
    private PApplet pApplet;

    public Drawer(PApplet pApplet){

        this.pApplet = pApplet;

    }

    /**
     * Method to draw a stick figure onto the screen
     *
     * All the dimensions for the stick figure should be set before this is called
     *
     * @param stickFigure
     */
    public void drawStickFigure(StickFigure stickFigure){

        ArrayList<StickFigure.BodyPart>  bodyParts = (ArrayList<StickFigure.BodyPart>) stickFigure.getBodyParts();

        pApplet.fill(0);

        for(int i = 0; i < bodyParts.size(); i++){
            if(bodyParts.get(i) instanceof StickFigure.LineComponent){
                pApplet.stroke(0);
                pApplet.strokeWeight(20);
                StickFigure.LineComponent lc = (StickFigure.LineComponent) bodyParts.get(i);
                pApplet.line((int) lc.getxStart(), (int) lc.getyStart(), (int) lc.getxEnd(), (int) lc.getyEnd());

            }else if(bodyParts.get(i) instanceof StickFigure.Head){

                StickFigure.Head h = (StickFigure.Head) bodyParts.get(i);
                pApplet.ellipse((int) h.getxCenter(), (int) h.getyCenter(), (int) h.getSize(), (int) h.getSize());
            }
        }


    }



}
