package smtagro.donald.com.Agents;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.valdesekamdem.library.mdtoast.MDToast;

import smtagro.donald.com.R;

public class GetLocation extends AppCompatActivity {
    private EditText mLongitude,mLatitude;
    private Button submitCoordinate;
    private DatabaseReference mFarmersDatabase;
    private String position;
    private KProgressHUD hud;

    private Double longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);


        hud = KProgressHUD.create(GetLocation.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Sending Coordinate...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);
        mFarmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            position = bundle.getString("position");
            latitude = bundle.getDouble("latitude");
            longitude = bundle.getDouble("longitude");

        }
        mLatitude = findViewById(R.id.tv_farmers_latitude);
        mLongitude = findViewById(R.id.tv_farmers_longitude);
        submitCoordinate = findViewById(R.id.submitCoordinate);

        mLongitude.setText(""+longitude);
        mLatitude.setText(""+latitude);

        submitCoordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hud.show();
                mFarmersDatabase.child(position).child("longitude").setValue(longitude);
                mFarmersDatabase.child(position).child("latitude").setValue(latitude)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    MDToast.makeText(getApplication(),"Location Added Successfully",
                                            MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();

                                    Intent intent = new Intent(GetLocation.this, AgentMenu.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    MDToast.makeText(getApplication(),"Failed to add Location",
                                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                                }
                            }
                        });

            }
        });

    }
}
