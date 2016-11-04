package com.devfest.musicstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Screen;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFireBaseAuth;
    DatabaseReference mDatabase;
    boolean initialLoad = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        mFireBaseAuth.signInAnonymously();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("0").getParent().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (initialLoad) {

                    // TODO initial Fetch
                    Screen screen = new Screen(dataSnapshot);
                    screen.getSections();
                    // now we get the Whole Screen and will pass the screen to Recycler Views
                    initialLoad = false;
                } else {
                    // TODO onChildAdded

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // TODO play with data when onChildChanged
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // TODO play with data when childElements were removed
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // TODO play with data when child element is moved
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
