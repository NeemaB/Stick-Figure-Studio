package processing.test.draw_stick_figure_anim.Animation_Fragments;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;

public class Draw_Stick_Figure_Anim extends PApplet {



  int index = 0;

  PFont f;
  int animationStartTime;
  int lastCharStart;

  //store AnimatedChar values in lists
  List<AnimatedChar> charArray = null;
  List<AnimatedChar> activeCharArray = null;


  public void setup(){



    f = createFont("sans-serif", 30, true);

    //determine animation start time
    animationStartTime = millis();

    //holds the time for the last character's animation sequence was started
    lastCharStart = millis();

    charArray = new ArrayList<AnimatedChar>();
    activeCharArray = new ArrayList<AnimatedChar>();

    //add characters
    charArray.add(new AnimatedChar('D', width/2 - 450, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('R', width/2 - 390, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('A', width/2 - 330, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('W', width/2 - 270, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('Y', width/2 - 140, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('O', width/2 - 80, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('U', width/2 - 20, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('R', width/2 + 40, AnimatedChar.startHeight1));
    charArray.add(new AnimatedChar('S', width/2 - 353, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('T', width/2 - 293, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('I', width/2 - 233, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('C', width/2 - 203, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('K', width/2 - 143, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('F', width/2 - 13, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('I', width/2 + 47, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('G', width/2 + 77, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('U', width/2 + 137, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('R', width/2 + 197, AnimatedChar.startHeight2));
    charArray.add(new AnimatedChar('E', width/2 + 257, AnimatedChar.startHeight2));

    //add first character to active characters list
    activeCharArray.add(charArray.get(index));
    index++;

    background(255);

  }



  public void draw(){

    //clear previous frame
    background(255);

    //continually determine the time for comparison purposes
    int newTime = millis();

    //only draw characters whose animation sequence has been initiated
    for(AnimatedChar ac : activeCharArray){
      ac.drawChar();
    }

    //add new character to activeCharArray after time delay
    if(activeCharArray.size() < charArray.size()){
      if(newTime - lastCharStart >= 80){
        activeCharArray.add(charArray.get(index));
        index++;
        lastCharStart = millis();
      }
    }


  }

  /**

   This is a helper class that represents a single animated character

   includes information about color, location and velocity as well
   as whether the character has completed its animation

   **/

  private class AnimatedChar{

    private static final int startHeight1 = 150;
    private static final int startHeight2 = 270;

    private float velocity;
    private final float acceleration = 0.035f;

    private final char character;
    private boolean completedAnimation = false;

    private int xPos;
    private float yPos;
    private int brightness;



    public AnimatedChar (char c, int xPos, int yPos){

      character = c;
      this.xPos= xPos;
      this.yPos = yPos;
      brightness = 0xFF;
      velocity = -2.0f;

    }

    //make the character gradually darker
    public void makeDarker(){
      this.brightness -=5;
    }

    //move the characters vertically until they reach their apex
    public void move(){
      if(!this.completedAnimation){

        this.velocity += this.acceleration;
        this.yPos += this.velocity;
        if(this.velocity >= 0){
          this.completedAnimation = true;
        }else{
          return;
        }
      }
    }

    //calls makeDarker() and move() to animate the characters
    public void drawChar(){

      fill(this.brightness);
      textSize(100);
      text(this.character, this.xPos, this.yPos);
      if(!(this.brightness <= 0)){
        makeDarker();
      }
      move();

    }

  }
  public void settings() {  size(1000, 1500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Draw_Stick_Figure_Anim" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
