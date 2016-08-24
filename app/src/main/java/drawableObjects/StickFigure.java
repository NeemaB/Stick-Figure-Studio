package drawableObjects;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

/**
 * Created by neema boutorabi on 2016-08-23.
 *
 * This class represents a standardised stick figure object, this will be utilized in the launch
 * screen and the activity designer section of the app
 */
public class StickFigure{

    //group together all drawable body parts in a list
    private List<BodyPart> bodyParts;

    //list of points that the user can interact with to move their stick figure
    private List<InteractivePoint> interactivePoints;

    private float xCenter;
    private float yCenter;

    private Head head;
    private LineComponent spine;
    private LineComponent upperArm1;
    private LineComponent upperArm2;
    private LineComponent lowerArm1;
    private LineComponent lowerArm2;
    private LineComponent upperLeg1;
    private LineComponent upperLeg2;
    private LineComponent lowerLeg1;
    private LineComponent lowerLeg2;

    /**
        Constructor for this class

        the only parameters that need to be specified are the
        xCenter and yCenter variables, every other item is drawn relative to these values

     **/

    public StickFigure(float xCenter, float yCenter) {

        this.xCenter = xCenter;
        this.yCenter = yCenter;


        this.spine = new LineComponent(xCenter, yCenter - 120, xCenter, yCenter + 120);
        this.head = new Head(xCenter, yCenter - 150, 60);


        this.upperArm1 = new LineComponent(xCenter, yCenter - 50, xCenter + 30, yCenter - 20);
        this.upperArm2 = new LineComponent(xCenter, yCenter - 50, xCenter - 30, yCenter - 20);
        this.lowerArm1 = new LineComponent(upperArm1.getxEnd(), upperArm1.getyEnd(), upperArm1.getxEnd() + 30, upperArm1.getyEnd() + 30);
        this.lowerArm2 = new LineComponent(upperArm2.getxEnd(), upperArm2.getyEnd(), upperArm2.getxEnd() - 30, upperArm2.getyEnd() + 30);
        this.upperLeg1 = new LineComponent(xCenter, yCenter + 120, xCenter + 40, yCenter + 160);
        this.upperLeg2 = new LineComponent(xCenter, yCenter + 120, xCenter - 40, yCenter + 160);
        this.lowerLeg1 = new LineComponent(upperLeg1.getxEnd(), upperLeg1.getyEnd(), upperLeg1.getxEnd() + 40, upperLeg1.getyEnd() + 40);
        this.lowerLeg2 = new LineComponent(upperLeg2.getxEnd(), upperLeg2.getyEnd(), upperLeg2.getxEnd() -40, upperLeg2.getyEnd() + 40);

        bodyParts = new ArrayList<BodyPart>();
        interactivePoints = new ArrayList<InteractivePoint>();

        //the order in which these objects are added matters since we will draw them in that order

        bodyParts.add(spine);
        bodyParts.add(head);
        bodyParts.add(upperArm1);
        bodyParts.add(upperArm2);
        bodyParts.add(upperLeg1);
        bodyParts.add(upperLeg2);
        bodyParts.add(lowerLeg1);
        bodyParts.add(lowerLeg2);
        bodyParts.add(lowerArm1);
        bodyParts.add(lowerArm2);

    }

    public List<BodyPart> getBodyParts (){

        return this.bodyParts;
    }

    //All body parts implement this interface, this is mainly here for code readability
    public interface BodyPart {

    }

    //all parts of the stick figure that are lines fall into this category
    //e.g arms and legs
    public static class LineComponent implements BodyPart {

        private float xStart;
        private float xEnd;

        private float yStart;
        private float yEnd;

        public LineComponent(float xStart, float yStart, float xEnd, float yEnd){
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }

        public float getxStart() {
            return this.xStart;
        }

        public float getyStart() {
            return this.yStart;
        }

        public float getxEnd() {
            return this.xEnd;
        }

        public float getyEnd() {
            return this.yEnd;
        }

    }


    public static class Head implements BodyPart  {

        float xCenter;
        float yCenter;
        float size;

        public Head (float xCenter, float yCenter, float size){

            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.size = size;
        }

        public float getxCenter(){
            return xCenter;
        }

        public float getyCenter(){
            return yCenter;
        }

        public float getSize(){
            return size;
        }

    }


    private static class InteractivePoint {

        float xCenter;
        float yCenter;
        float size;

        public InteractivePoint(float xCenter, float yCenter, float size){
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.size = size;

        }

    }


}
