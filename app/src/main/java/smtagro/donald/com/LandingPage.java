package smtagro.donald.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import smtagro.donald.com.Admin.AdminLogin;
import smtagro.donald.com.Agents.AgentLogin;

public class LandingPage extends AppCompatActivity {
    private Button mAdminLogin,mAgentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        mAdminLogin = findViewById(R.id.bt_admin);
        mAgentLogin = findViewById(R.id.bt_agent);


        mAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent admin_login = new Intent(LandingPage.this, AdminLogin.class);
                admin_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                admin_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                admin_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(admin_login);
            }
        });

        mAgentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agent_login = new Intent(LandingPage.this, AgentLogin.class);
                agent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                agent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                agent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(agent_login);
            }
        });
    }
}
