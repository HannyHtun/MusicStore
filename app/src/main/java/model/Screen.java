package model;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import util.ScreenParser;


public class Screen {
    public static final String SCREEN_TITLE = "title";
    public static final String SECTIONS = "sections";
    DataSnapshot mDataSnapShot;
    ArrayList<Section> mSections = new ArrayList<>();
    ScreenParser mScreenParser;

    public Screen(DataSnapshot snapshot) {
        this.mDataSnapShot = snapshot;
    }

    /**
     * This method returns screen title
     * @return String title
     */
    public String getTitle() {
        if (mDataSnapShot.hasChild(SCREEN_TITLE)) {
            Log.e("Screen Title", mDataSnapShot.child(SCREEN_TITLE).getValue().toString());
            return mDataSnapShot.child("title").getValue().toString();
        }
        return "";
    }

    /**
     * This method returns ArrayList of sections from Screeen
     *
     * @return ArrayList<Section>
     */
    public ArrayList<Section> getSections() {
        // TODO ArrayList Section to be returned
        this.mScreenParser = new ScreenParser(mDataSnapShot);
        if (mDataSnapShot.hasChild(SECTIONS)) {
            for (DataSnapshot section : mDataSnapShot.child(SECTIONS).getChildren()) {
                this.mSections.add(this.mScreenParser.parseSection(section));
            }
        }

        return mSections;
    }
}
