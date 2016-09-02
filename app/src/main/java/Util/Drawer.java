package Util;

import java.util.ArrayList;

import DrawableObjects.StickFigure;
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

    public void clearRegion(double x1, double x2, double y1, double y2, ColorVector cv ){

        pApplet.fill(cv.R(), cv.G(), cv.B());
        pApplet.noStroke();
        pApplet.rect((float) x1, (float) y1, (float) x2, (float) y2);

    }


    public static class ColorVector {
        private int R;
        private int G;
        private int B;

        public ColorVector(int R, int G, int B){
            this.R = R;
            this.G = G;
            this.B = B;
        }

        public ColorVector(int val){
            this.R = val;
            this.G = val;
            this.B = val;
        }

        public int R(){
            return R;
        }

        public int G(){
            return G;
        }

        public int B(){
            return B;
        }
    }

}
