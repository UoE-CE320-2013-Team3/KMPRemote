package tests.manual;

import inputControllers.PresentationInputControl;

/**
 * Created with IntelliJ IDEA.
 * User: mhpric & John
 * Date: 04/12/13
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */

/**
 * Test Purpose:
 *  This test is designed to test the PresentationInputControl class is producing
 *  the correct notes from the given powerpoint and returning them in the correct
 *  JSON format
 *
 *  To use:
 *      Add the
 */
public class ManualPresentationControlTest {
    public static void main(String args[]) {
    //insert filePath here
    inputControllers.PresentationInputControl presentationControl
            = new PresentationInputControl("M:\\pc\\desktop\\test.ppt");
    System.out.println(presentationControl.getNotesAsJSON());
    }
}
