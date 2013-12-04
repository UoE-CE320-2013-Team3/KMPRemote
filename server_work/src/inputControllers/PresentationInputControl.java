package inputControllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
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

    public static LinkedList<String> getNotes(String filePath) {
        LinkedList<String> results = new LinkedList<String>();

        //read the powerpoint
        SlideShow slideShow = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            slideShow = new SlideShow(new HSLFSlideShow(inputStream));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        slideShow.getNotes();

        //get the slides
        Slide[] slides = slideShow.getSlides();

        //loop over all slides
        for (Slide slide : slides) {
            //get the notes for this slide
            Notes notes = slide.getNotesSheet();
            //get the "text runs" that are part of this slide
            TextRun[] textRuns = notes.getTextRuns();
            //build a string with the text from all the runs in the slide
            StringBuilder stringBuilder = new StringBuilder();
            for (TextRun textRun : textRuns) {
                stringBuilder.append(textRun.getRawText());
            }
            //add the resulting string to the results
            String alteredNotes = stringBuilder.toString();
            alteredNotes = alteredNotes.replaceAll("\\r", System.getProperty("line.separator"));
            results.add(alteredNotes);

        }

        return results;
    }

    String applyJSONFormatToArray(List<String> notes) {
        Map<Integer, String> myMap = new TreeMap<Integer, String>();

        int i = 0;
        for (String note : notes) {
            myMap.put(++i, note.substring(0, note.length()-1));
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(myMap);


    }

    public String getNotesAsJSON() {
        return applyJSONFormatToArray(notes);
    }


}
