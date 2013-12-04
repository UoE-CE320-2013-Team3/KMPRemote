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
 *      Add the filepath of the powerpoint (must be 2007 or earlier (e.g. ppt))
 *      Run the program and you should see an output to the standard output stream
 *      in the following JSON format:
 *          {"1":"This is a test","2":"This is a second test"}
 *      Where the number is the slide number and the text is the text contained
 *      within the notes for that page
 */
public class ManualPresentationControlTest {
    public static void main(String args[]) {
    //insert filePath here
    inputControllers.PresentationInputControl presentationControl
            = new PresentationInputControl("M:\\pc\\desktop\\test.ppt");
    System.out.println(presentationControl.getNotesAsJSON());
    }
}
