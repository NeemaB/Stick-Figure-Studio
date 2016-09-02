package Util;

/**
 * Created by neema on 2016-08-31.
 *
 * Utility class for math functions and calculations
 */
public class MathUtil {


    /**
     * This method determines the shortest distance from a point to a line segment
     *
     * Algorithm :
     *
     * 1) Construct an orthogonal vector to the line segment
     * 2) Check to see if a line with this vector direction originating from the point will intersect our line segment
     * 3) If yes then the distance of this orthogonal line is the shortest distance
     * 4) If not, measure the distance between the point and both the start and end co-ordinates of the line segment
     * 5) Return the smallest of these two values
     *
     *
     * @param xStart
     *              x co-ordinate of start of line segment
     * @param xEnd
     *              x co-ordinate of end of line segment
     * @param yStart
     *              y co-ordinate of start of line segment
     * @param yEnd
     *              y co-ordinate of end of line segment
     * @param x
     *              x co-ordinate of point
     * @param y
     *              y co-ordinate of point
     * @return
     *              shortest distance in pixels
     */
    public static double shortestDistanceToLine(double xStart, double xEnd, double yStart, double yEnd, double x, double y) {

        //initialize distance 1 to highest possible value
        double distance1 = Integer.MAX_VALUE;
        double distance2;
        double distance3;

        //check if line segment is vertical or horizontal
        if (Math.abs(xEnd - xStart) > 0.01 && Math.abs(yEnd - yStart) > 0.01) {

            double a = xEnd - xStart;
            double b = yEnd - yStart;
            double c = -((Math.pow(a, 2)) / b);

            //t is the free parameter for our line from the point to the line
            double t = (xStart - (a / b) * yStart - x + y) / (a - c * (a / b));

            //s is the free parameter for the line containing the line segment
            double s = ((t * (a) + x - xStart) / (a));

            //if there is an intersection then our value for s must be between 0 and 1
            if (s > 0.0 && s < 1.0) {
                double intersectX = s * a + xStart;
                double intersectY = s * b + yStart;

                //get distance from point to intersect
                distance1 = getDistance(x, y, intersectX, intersectY);

            }
            //line is vertical, orthogonal distance to line is simply the difference in x co-ordinates
        }else if (Math.abs(xEnd - xStart) <= 0.01){
            if(yEnd > yStart){
                //check if point lies between starting and ending y co-ordinates of line
                if(y <= yEnd && y >= yStart){
                    distance1 = Math.abs(xEnd - x);
                }
            }else{
                //check if point lies between ending and starting y co-ordinates of line
                if(y <= yStart && y >= yEnd){
                    distance1 = Math.abs(xEnd - x);
                }
            }
            //line is horizontal, orthogonal distance to line is simply the difference in y co-ordinates
        }else if (Math.abs(yEnd - yStart) <= 0.01){
            if(xEnd > xStart){
                //check if point lies between starting and ending x co-ordinates of line
                if(x <= xEnd && x >= xStart){
                    distance1 = Math.abs(yEnd - y);
                }
            }else{
                //check if point lies between ending and starting x co-ordinates of line
                if(x <= xStart && x >= xEnd){
                    distance1 = Math.abs(yEnd - y);
                }
            }
        }

        //get distance from point to end of line
        distance2 = getDistance(x, y, xEnd, yEnd);
        //get distance from point to start of line
        distance3 = getDistance(x, y, xStart, yStart);
            
            
        double shortestDistance = Math.min(distance1, distance2);
        shortestDistance = Math.min(shortestDistance, distance3);

        System.out.println(shortestDistance);
            
        return shortestDistance;


    }
    
    //returns euclidean distance between two points

    public static double getDistance(double x1, double y1, double x2, double y2){

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
