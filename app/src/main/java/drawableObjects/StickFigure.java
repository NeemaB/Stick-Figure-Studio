package DrawableObjects;

import java.util.ArrayList;
import java.util.List;

import Util.MathUtil;

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

    private double xCenter;
    private double yCenter;

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

    public StickFigure(double xCenter, double yCenter) {

        this.xCenter = xCenter;
        this.yCenter = yCenter;

        //all of the stick figure dimensions will be calculated according to this value
        double size = 350;

        this.spine = new LineComponent(xCenter, yCenter - size/2 , xCenter, yCenter + size/2);


        this.head = new Head(xCenter, yCenter - (size / 2 + size / 7.5), size / 3.75);


        this.upperArm1 = new LineComponent(xCenter, yCenter - size/6 , xCenter + size/6 , yCenter);
        this.upperArm2 = new LineComponent(xCenter, yCenter - size/6, xCenter - size/6, yCenter);
        this.lowerArm1 = new LineComponent(upperArm1.getxEnd(), upperArm1.getyEnd(), upperArm1.getxEnd() + size/6, upperArm1.getyEnd() + size/6);
        this.lowerArm2 = new LineComponent(upperArm2.getxEnd(), upperArm2.getyEnd(), upperArm2.getxEnd() - size/6, upperArm2.getyEnd() + size/6);
        this.upperLeg1 = new LineComponent(xCenter, yCenter + size/2, xCenter + size/5.45, yCenter + size/1.46);
        this.upperLeg2 = new LineComponent(xCenter, yCenter + size/2, xCenter - size/5.45, yCenter + size/1.46);
        this.lowerLeg1 = new LineComponent(upperLeg1.getxEnd(), upperLeg1.getyEnd(), upperLeg1.getxEnd() + size/5.45, upperLeg1.getyEnd() + size/5.45);
        this.lowerLeg2 = new LineComponent(upperLeg2.getxEnd(), upperLeg2.getyEnd(), upperLeg2.getxEnd() - size/5.45, upperLeg2.getyEnd() + size/5.45);

        bodyParts = new ArrayList<BodyPart>();
        interactivePoints = new ArrayList<InteractivePoint>();

        //the order in which these objects are added matters since some of their dimensions depend on one another

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

    /**
     * This method is called when the user touches the screen, the return value represents whether the user
     * clicked on the stick figure or not
     *
     *
     * @param mouseX
     *              The x position of the touch event
     * @param mouseY
     *              The y position of the touch event
     *
     *
     */
    public boolean wasTouched(int mouseX, int mouseY){

        //iterate through all body parts and check if any of them were touched
        for(BodyPart bp : bodyParts){
            if(bp.wasTouched(mouseX, mouseY)){
                return true;
            }
        }

        return false;

    }

    //All body parts implement this interface, this is mainly here for code readability
    public interface BodyPart {

       public boolean wasTouched(int mouseX, int mouseY);

    }

    //all parts of the stick figure that are lines fall into this category
    //e.g arms and legs
    public static class LineComponent implements BodyPart {

        private double xStart;
        private double xEnd;

        private double yStart;
        private double yEnd;

        public LineComponent(double xStart, double yStart, double xEnd, double yEnd){
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }

        @Override
        public boolean wasTouched(int mouseX, int mouseY){

            //get the shortest distance from the point to this line segment
            double distance = MathUtil.shortestDistanceToLine(xStart, xEnd, yStart, yEnd, mouseX, mouseY);

            //check if distance is below threshold
            if(distance <= 40){
                return true;
            }else {

                return false;
            }
        }

        public double getxStart() {
            return this.xStart;
        }

        public double getyStart() {
            return this.yStart;
        }

        public double getxEnd() {
            return this.xEnd;
        }

        public double getyEnd() {
            return this.yEnd;
        }



    }

    //This represents the stick figures head
    public static class Head implements BodyPart  {

        double xCenter;
        double yCenter;
        double size;

        public Head (double xCenter, double yCenter, double size){

            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.size = size;
        }

        public double getxCenter(){
            return xCenter;
        }

        public double getyCenter(){
            return yCenter;
        }

        public double getSize(){
            return size;
        }

        public boolean wasTouched(int mouseX, int mouseY){

            //get radial distance from center of head to point of touch event
            double distance = MathUtil.getDistance(xCenter, yCenter, mouseX, mouseY);

            //take away radius of head and check if remaining distance is below threshold
            if((distance - size/2) < 20){
                return true;
            }else {
                return false;
            }
        }

    }

    //represents a point of interaction that allows the user to move parts of the stick figure
    private static class InteractivePoint {

        double xCenter;
        double yCenter;
        double size;

        public InteractivePoint(double xCenter, double yCenter, double size){
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.size = size;

        }

    }


}
