package inputControllers; /**
 * Created with IntelliJ IDEA.
 * User: john, mhpric
 * Date: 11/14/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */

import java.awt.*;
import java.awt.event.InputEvent;

//TODO Implement mouse scroll

public class MouseInputControl {

    Robot robot;

    public MouseInputControl() throws AWTException {
        robot = new Robot();
    }

    private int getXCoordinateOfMouse() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point getCursorLocation = pointerInfo.getLocation();
        return (int) getCursorLocation.getX();
    }

    private int getYCoordinateOfMouse() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point getCursorLocation = pointerInfo.getLocation();
        return (int) getCursorLocation.getY();
    }

    public void moveMouseUp(int pixelsToMove) {
        int XCoordinateOfMouse = getXCoordinateOfMouse();
        int YCoordinateOfMouse = getYCoordinateOfMouse();

        robot.mouseMove(XCoordinateOfMouse, YCoordinateOfMouse - pixelsToMove);
    }

    public void moveMouseLeft(int pixelsToMove) {
        int XCoordinateOfMouse = getXCoordinateOfMouse();
        int YCoordinateOfMouse = getYCoordinateOfMouse();

        robot.mouseMove(XCoordinateOfMouse - pixelsToMove, YCoordinateOfMouse);
    }

    public void moveMouseRight(int pixelsToMove) {
        int XCoordinateOfMouse = getXCoordinateOfMouse();
        int YCoordinateOfMouse = getYCoordinateOfMouse();

        robot.mouseMove(XCoordinateOfMouse + pixelsToMove, YCoordinateOfMouse);
    }

    public void moveMouseDown(int pixelsToMove) {
        int XCoordinateOfMouse = getXCoordinateOfMouse();
        int YCoordinateOfMouse = getYCoordinateOfMouse();

        robot.mouseMove(XCoordinateOfMouse, YCoordinateOfMouse + pixelsToMove);
    }

    public void leftMouseButtonToggle() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void leftMouseButtonHold() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void leftMouseButtonRelease() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void rightMouseButtonToggle() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

    }

    public void rollMouseWheelDown(int numberOfNotches) {
        robot.mouseWheel(numberOfNotches);
    }

    public void rollMouseWheelUp(int numberOfNotches) {
        numberOfNotches = numberOfNotches*(-1);
        robot.mouseWheel(numberOfNotches);
    }
}
