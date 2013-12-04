package inputControllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.model.TextRun;

/**
 * Created with IntelliJ IDEA.
 * User: mhpric & John
 * Date: 04/12/13
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class PresentationInputControl {
    //TODO return the text contained in powerpoints note section

    List<String> notes;

    public PresentationInputControl(String filePath) {
        notes = getNotes(filePath);
    }

    private static LinkedList<String> getNotes(String filePath) throws InvalidPowerpointFileException {
        LinkedList<String> results = new LinkedList<String>();

        //read the powerpoint
        SlideShow slideShow = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            slideShow = new SlideShow(new HSLFSlideShow(inputStream));
            inputStream.close();
        } catch (IOException e) {
            throw new InvalidPowerpointFileException(filePath);
        }
        slideShow.getNotes();

        //get the slides
        Slide[] slides = slideShow.getSlides();

        //loop over all slides
        for (Slide slide : slides) {
            //get the notes for this slide
            Notes notes = slide.getNotesSheet();
            if (notes != null) {
            //get the "text runs" that are part of this slide
            TextRun[] textRuns = notes.getTextRuns();
            //build a string with the text from all the runs in the slide
            StringBuilder stringBuilder = new StringBuilder();
            for (TextRun textRun : textRuns) {
                stringBuilder.append(textRun.getRawText());
            }
            //add the resulting string to the results
            String alteredNotes = stringBuilder.toString();
            alteredNotes = alteredNotes.replaceAll("\\r\\n", "\n");
            alteredNotes = alteredNotes.replaceAll("\\r", "\n");
            results.add(alteredNotes.substring(0, alteredNotes.length()-1));

            }
            else {
                results.add("No notes");
            }
        }

        return results;
    }

    private String applyJSONFormatToArray(List<String> notes) {
        Map<Integer, String> myMap = new TreeMap<Integer, String>();

        int i = 0;
        for (String note : notes) {
            myMap.put(++i, note);
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(myMap);


    }

    public String getNotesAsJSON() {
        return applyJSONFormatToArray(notes);
    }

    public static class InvalidPowerpointFileException extends RuntimeException {
        private InvalidPowerpointFileException(String filePath) {
            super(filePath + " is not a valid powerpoint file. Please use a powerpoint version " +
                    "from 2007 or earlier (.ppt is guaranteed to be 2003 or earlier)");
        }
    }

}
