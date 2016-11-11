package model;

import java.util.ArrayList;

/**
 * Created by win on 11/4/16.
 */

public class Section {
    private String title;
    private String type;
    private Row mRow;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Row getmRow() {
        return mRow;
    }

    public void setmRow(Row mRow) {
        this.mRow = mRow;
    }
}
