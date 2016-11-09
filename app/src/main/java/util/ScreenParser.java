package util;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import model.Album;
import model.Genere;
import model.Row;
import model.Section;

/**
 * Created by win on 11/4/16.
 */

public class ScreenParser {

    public static final String SECTION_TITLE = "title";
    public static final String SECTION_TYPE = "type";
    public static final String SECTION_ROWS = "row";

    DataSnapshot dataSnapshot;

    public ScreenParser(DataSnapshot ds) {
        // Return Screen Object by Type
        this.dataSnapshot = ds;
    }

    /**
     * This method is to parse each Section according DataSnapShot
     *
     * @param ds DataSnapshot
     * @return Object Section
     */
    public Section parseSection(DataSnapshot ds) {
        // Section Title parse here
        Section section = new Section();
        if (ds.hasChild(SECTION_TITLE)) {
            section.setTitle(ds.child(SECTION_TITLE).getValue().toString());


        }
        //Section Type
        if (ds.hasChild(SECTION_TYPE)) {
            section.setType(ds.child(SECTION_TYPE).getValue().toString());
        }

        // Parse child Rows
        if (ds.hasChild(SECTION_ROWS)) {
            // parse Albums
            section.setmRow(parseRow(ds.child("row")));
        }

        // You can extend sessions with more view models here
        return section;
    }

    /**
     * This method is to parse rows according DataSnapShot
     *
     * @param ds DataSnapShot
     * @return Object Row
     */
    public Row parseRow(DataSnapshot ds) {
        Row row = new Row();
        for(DataSnapshot rowData : ds.child("data").getChildren()){
            Log.e("PARSER",rowData.child("type").getValue().toString());
            if(rowData.child("type").getValue().toString().equals("genere")){
                // TODO GENERE PARSING
                row.setGeneres(parseGenere(ds.child("data")));
            }

            if(rowData.child("type").getValue().toString().equals("album")){
                // TODO GENERE PARSING
                row.setAlbums(parseAlbums(ds.child("data")));

            }


        }

        return row;
    }


    /**
     * This method is to parse Albums according to DataSnapShot
     *
     * @param ds DataSnapShot
     * @return ArrayList<Album>
     */
    public ArrayList<Album> parseAlbums(DataSnapshot ds) {
        ArrayList<Album> albumList = new ArrayList<>();
        for (DataSnapshot album : ds.getChildren()) {
            Album a = new Album();
            a.setTitle(album.child("title").getValue().toString());
            a.setFollowers(album.child("followers").getValue().toString());
            a.setImgUrl(album.child("img").getValue().toString());
            a.setType(album.child("type").getValue().toString());

            albumList.add(a);
        }
        return albumList;
    }


    /**
     * This method is to parse Genere according to DataSnapShot
     * @param ds
     * @return ArrayList<Genere>
     */
    public ArrayList<Genere> parseGenere(DataSnapshot ds){
        ArrayList<Genere> genereList = new ArrayList<>();
        for(DataSnapshot genere: ds.getChildren()){
            Genere g = new Genere();
            g.setTitle(genere.hasChild("title")?genere.child("title").getValue().toString():"");
            g.setType(genere.hasChild("type")?genere.child("type").getValue().toString():"");
            genereList.add(g);
        }
        return genereList;
    }


}
