package smtagro.donald.com.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import smtagro.donald.com.R;
import smtagro.donald.com.models.AgentModel;

public class AgentDetails extends AppCompatActivity {
    private TextView uNames,uPhone,uAddress,uLga,uEmail,uAgentId;
    private ImageView uImage;
    private DatabaseReference agentDatabase;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

        uNames = findViewById(R.id.detail_agent_name);
        uPhone = findViewById(R.id.tv_detail_phone);
        uAddress = findViewById(R.id.detail_agent_address);
        uLga = findViewById(R.id.detail_agent_lga);
        uEmail = findViewById(R.id.detail_agent_email);
        uImage = findViewById(R.id.agent_detail_image);
        uAgentId = findViewById(R.id.detail_agent_id);

        agentDatabase = FirebaseDatabase.getInstance().getReference().child("Agents");

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            position = extras.getString("position");
            if (position!=null){
                DatabaseReference userRef = agentDatabase.child(position);

                Log.d("userref",""+userRef);
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Log.d("ds",""+ds);

                        AgentModel member = dataSnapshot.getValue(AgentModel.class);
                        uNames.setText(member.getfName()+" "+member.getlName());
                        uPhone.setText(member.getPhone());
                        uAddress.setText(member.getAddress());
                        uEmail.setText(member.getEmail());
                        uLga.setText(member.getLga());
                        uAgentId.setText(member.getAgentId());

                        String profile_image = member.getImage();
                        Picasso.with(AgentDetails.this).load(profile_image).into(uImage);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                userRef.addListenerForSingleValueEvent(valueEventListener);


            }



        }

    }
}
