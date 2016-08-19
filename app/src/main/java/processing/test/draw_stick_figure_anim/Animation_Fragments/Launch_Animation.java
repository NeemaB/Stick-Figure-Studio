package processing.test.draw_stick_figure_anim.Animation_Fragments;

import android.content.Intent;
import android.os.Bundle;

import processing.core.PApplet;

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PFont;
import processing.test.draw_stick_figure_anim.Activities.MenuActivity;

/**
 * Created by neema on 2016-08-18.
 */



public class Launch_Animation extends PApplet {

    PFont f;
    int startTime;
    int lastTime;

    List<RotatedChar> title;

    public void setup(){


        float lastXPos = width/9;
        float lastYPos = height/9 + 60;

        title = new ArrayList<RotatedChar>();

        title.add(new RotatedChar('S', lastXPos, lastYPos, -0.55f));
        title.add(new RotatedChar('T', lastXPos+=65, lastYPos-=39, -.28f));
        title.add(new RotatedChar('I', lastXPos+=90, lastYPos-=25, -.25f));
        title.add(new RotatedChar('C', lastXPos+=45, lastYPos-=12, -0.12f));
        title.add(new RotatedChar('K', lastXPos+=95, lastYPos-=7, -0.05f));

        lastXPos = width/9;

        title.add(new RotatedChar('F', lastXPos, lastYPos+=240, -0.50f));
        title.add(new RotatedChar('I', lastXPos+=75, lastYPos-=35, -0.38f));
        title.add(new RotatedChar('G', lastXPos+=44, lastYPos-=22, -0.25f));
        title.add(new RotatedChar('U', lastXPos+=105, lastYPos-=24, -0.20f));
        title.add(new RotatedChar('R', lastXPos+=100, lastYPos-=17, -0.14f));
        title.add(new RotatedChar('E', lastXPos+=102, lastYPos-=10, -0.05f));



        lastXPos = width/9 + 42;

        title.add(new RotatedChar('S', lastXPos, lastYPos+=250, -0.55f));
        title.add(new RotatedChar('T', lastXPos+=72, lastYPos-=30, -0.35f));
        title.add(new RotatedChar('U', lastXPos+=80, lastYPos-=27, -0.23f));
        title.add(new RotatedChar('D', lastXPos+=95, lastYPos-=22, -0.17f));
        title.add(new RotatedChar('I', lastXPos+=104, lastYPos-=17, -0.14f));
        title.add(new RotatedChar('O', lastXPos+=45, lastYPos-=5, -0.10f));


        String [] fonts = PFont.list();

        for(String s : fonts){
            System.out.println(s);
        }

        f = createFont("Serif-Bold", 28);
        textFont(f);

        background(255, 255, 0);


        drawMiniStickFigures();

        for(RotatedChar c : title){
            c.drawText();
        }

        drawLoadingText(width/2, height - 400);

        lastTime = millis();
        startTime = millis();



    }

    public void draw(){


        noStroke();

        if((millis() - lastTime) < 1000){
            fill(0);
            ellipse(width/2, height - 440, 50, 50);
            ellipse(width/2 + 80, height - 440, 50, 50);
            fill(0, 100, 255);
            ellipse(width/2 - 80, height - 440, 50, 50);
        }else if (((millis() - lastTime) >= 1000) && (millis() - lastTime) <= 2000){
            fill(0);
            ellipse(width/2 - 80, height - 440, 50, 50);
            ellipse(width/2 + 80, height - 440, 50, 50);
            fill(0, 100, 255);
            ellipse(width/2, height - 440, 50, 50);
        }else if (((millis() - lastTime) >= 2000 && (millis() - lastTime) <= 3000)){
            fill(0);
            ellipse(width/2 - 80, height - 440, 50, 50);
            ellipse(width/2, height - 440, 50, 50);
            fill(0, 100, 255);
            ellipse(width/2 + 80, height - 440, 50, 50);
        }else{
            lastTime = millis();
        }

        if((millis() - startTime) > 5000){
            finish();
        }



    }
    public void drawLoadingText(int xPos, int yPos){
        noStroke();
        fill(0);

        textSize(90);
        textAlign(CENTER);
        text("Loading", xPos, yPos + 100);

    }

    public void drawMiniStickFigures(){

        fill(0);
        strokeWeight(5);
        ellipse(width/9 + 500 , height/9 - 54, 20, 20);
        line(width/9 + 500, height/9 - 54, width/9 + 475, height/9 - 19);
        line(width/9 + 475, height/9 - 19, width/9 + 485, height/9 + 6);
        line(width/9 + 475, height/9 - 19, width/9 + 460, height/9 + 6);
        line(width/9 + 489, height/9 - 39, width/9 + 462, height/9 - 16);

        ellipse(width/9 + 222, height/9 + 195, 20, 20);
        line(width/9 + 222, height/9 + 195, width/9 + 222, height/9 + 235);
        line(width/9 + 222, height/9 + 218, width/9 + 195, height/9 + 197);
        line(width/9 + 222, height/9 + 235, width/9 + 244, height/9 + 251);
        line(width/9 + 222, height/9 + 235, width/9 + 233, height/9 + 256);

        ellipse(width/9 + 75, height/9 - 120, 20, 20);
        line(width/9 + 75, height/9 - 120, width/9 + 44, height/9 - 80);
        line(width/9 + 44, height/9 - 80, width/9 + 36, height/9 - 50);
        line(width/9 + 44, height/9 - 80, width/9 + 46, height/9 - 47);
        line(width/9 + 60, height/9 - 100, width/9 + 72, height/9 - 80);
        line(width/9 + 60, height/9 - 100, width/9 + 62, height/9 - 76);
    }


    private class RotatedChar {

        private float rotation;
        private float xPos;
        private float yPos;

        private char character;

        public RotatedChar(char character, float xPos, float yPos, float rotation){

            this.xPos = xPos;
            this.yPos = yPos;
            this.character = character;
            this.rotation = rotation;
        }

        public void drawText(){


            resetMatrix();
            pushMatrix();
            translate(this.xPos, this.yPos);
            rotate(this.rotation);

            fill(0);
            textSize(140);
            text(this.character, 0, 0);

            popMatrix();


        }

    }

    private void finish(){
        getActivity().finish();
        startActivity(new Intent(getActivity(), MenuActivity.class));
    }

    private interface Drawable {

        public void drawObject();


    }

    private class Line implements Drawable{

        private float xStart;
        private float xEnd;

        private float yStart;
        private float yEnd;

        public void drawObject(){
            line(xStart, yStart, xEnd, yEnd);
        }



    }
    public void settings() {

        Bundle arguments = getArguments();

        size((int) arguments.getFloat("width"), (int) arguments.getFloat("height"));

    }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Launch_Animation" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}