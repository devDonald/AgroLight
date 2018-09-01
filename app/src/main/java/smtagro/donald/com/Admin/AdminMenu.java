package smtagro.donald.com.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import smtagro.donald.com.Agents.ViewFarmers;
import smtagro.donald.com.LandingPage;
import smtagro.donald.com.R;

public class AdminMenu extends AppCompatActivity {
    private Button showFarms,searchAgent,searchFarmer,addAgent, viewAgents,viewFarmers,logout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        mAuth = FirebaseAuth.getInstance();

        showFarms =findViewById(R.id.btn_admin_show_farms);
        searchAgent = findViewById(R.id.btn_admin_search_agent);
        searchFarmer = findViewById(R.id.btn_admin_search_farmer);
        addAgent = findViewById(R.id.btn_admin_register_agent);
        viewAgents = findViewById(R.id.btn_admin_view_agents);
        viewFarmers = findViewById(R.id.btn_admin_view_farmers);
        logout = findViewById(R.id.btn_admin_logout);

        showFarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showFarms = new Intent(AdminMenu.this,ShowFarms.class);
                startActivity(showFarms);
            }
        });

        searchAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchAgent = new Intent(AdminMenu.this,SearchAgent.class);
                startActivity(searchAgent);
            }
        });
        searchFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent searchFarmer = new Intent(AdminMenu.this,SearchFarmer.class);
                startActivity(searchFarmer);
            }
        });
        addAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAgent = new Intent(AdminMenu.this,AddAgents.class);
                startActivity(addAgent);
            }
        });
        viewAgents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAgents = new Intent(AdminMenu.this,ViewAgents.class);
                startActivity(viewAgents);
            }
        });
        viewFarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewFarmers = new Intent(AdminMenu.this,ViewFarmers.class);
                startActivity(viewFarmers);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent landingPage = new Intent(AdminMenu.this,LandingPage.class);
                landingPage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                landingPage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(landingPage);
                finish();
            }
        });

    }
}
