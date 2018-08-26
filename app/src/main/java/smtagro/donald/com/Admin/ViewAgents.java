package smtagro.donald.com.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import smtagro.donald.com.R;

public class ViewAgents extends AppCompatActivity {
    private DatabaseReference agentsDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agents);


    }
}
