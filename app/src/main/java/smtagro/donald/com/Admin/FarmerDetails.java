package smtagro.donald.com.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import smtagro.donald.com.Agents.GetLocation;
import smtagro.donald.com.R;
import smtagro.donald.com.models.FarmersModel;

public class FarmerDetails extends AppCompatActivity {
    private TextView mNames,mAge,mHouseHoldSize,mPhone,mFarmSize,mFarmLocation,mCooperativeLoc;
    private TextView mDistanceToMarket,mAvgIncomeFarming,mAvgIncomeNonFarming,mMajorCrop,mBVN;
    private TextView mMembership,mEnteredBy,mModeOfIdent,mLga,mGender,mState;
    private TextView mFederalWard,mVillage,mFarmerType,mEducationalQual,mMaritalStatus;
    private TextView mFIN;
    private ImageView mfarmerImage,mIdImage;
    private DatabaseReference farmerDatabase;
    private String position;
    private Double longitude,latitude;
    private Button getLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_details);
        mFarmLocation = findViewById(R.id.detail_farmer_farm_location);
        mCooperativeLoc = findViewById(R.id.farmer_detail_corp_loc);
        mModeOfIdent = findViewById(R.id.farmer_detail_identification);
        mFederalWard = findViewById(R.id.farmer_detail_ward);
        mVillage = findViewById(R.id.farmer_detail_village);
        mFarmerType = findViewById(R.id.farmer_detail_farmer_type);
        mEducationalQual = findViewById(R.id.farmer_detail_qual);
        mNames = findViewById(R.id.farmer_detail_names);
        mAge =findViewById(R.id.farmer_detail_age);
        mPhone = findViewById(R.id.farmer_detail_phone);
        mFarmSize = findViewById(R.id.farmer_detail_farmsize);
        mDistanceToMarket = findViewById(R.id.farmer_detail_distance_market);
        mAvgIncomeFarming = findViewById(R.id.farmer_detail_avg_income_farming);
        mMajorCrop = findViewById(R.id.farmer_detail_major_crops);
        mBVN = findViewById(R.id.farmer_detail_bvn);
        mMembership = findViewById(R.id.farmer_detail_membership);
        mEnteredBy = findViewById(R.id.farmer_detail_agent_name);
        mfarmerImage = findViewById(R.id.farmer_detail_image);
        mMaritalStatus = findViewById(R.id.farmer_detail_marital);
        mGender = findViewById(R.id.farmer_detail_gender);
        mState = findViewById(R.id.farmer_detail_state);
        mLga = findViewById(R.id.farmer_detail_lga);
        mIdImage = findViewById(R.id.farmer_detail_id_image);
        mAvgIncomeNonFarming = findViewById(R.id.farmer_detail_avg_incomeN_farming);
        mHouseHoldSize = findViewById(R.id.farmer_detail_houseSize);
        mFIN = findViewById(R.id.farmer_detail_FIN);
        getLocation = findViewById(R.id.bt_get_farmer_location);



        farmerDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            position = extras.getString("position");
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
            Log.d("latitude",""+latitude);
            if (position!=null){
                DatabaseReference userRef = farmerDatabase.child(position);

                Log.d("userref",""+userRef);
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Log.d("ds",""+ds);

                        FarmersModel member = dataSnapshot.getValue(FarmersModel.class);
                        mFarmerType.setText("Farmer Type: "+member.getFarmer_existance());
                        mAge.setText("Age: "+member.getAge()+" Years");
                        mAvgIncomeFarming.setText("Average Income on Farming: N "+member.getAvg_income_farming());
                        mNames.setText("Names: "+member.getNames());
                        mPhone.setText("Phone: "+member.getPhone_number());
                        mBVN.setText("BVN: "+member.getBvn());
                        mCooperativeLoc.setText("Cooperative Location: "+member.getCooperative_location());
                        mDistanceToMarket.setText("Distance of Farm to Market: "+member.getDistance_to_market()+" KM");
                        mEducationalQual.setText("Educational Qualification: "+member.getHighestQualification());
                        mEnteredBy.setText("Agent Name: "+member.getAgentName());
                        mFarmLocation.setText("Farm Location: "+member.getFarmLocation());
                        mFarmSize.setText("Farm Size: "+member.getFarm_size()+" Hectares");
                        mMajorCrop.setText("Major Crops: "+member.getMajor_crops());
                        mGender.setText("Gender: "+member.getGender());
                        mState.setText("State of Operation: "+member.getState());
                        mLga.setText("Local Government: "+member.getLga());
                        mMembership.setText("Cooperative Name: "+member.getCooperative_membership());
                        mModeOfIdent.setText("Mode of Identification: "+member.getModeOfIdentification());
                        mVillage.setText("Village: "+member.getVillage());
                        mFederalWard.setText("Federal Ward: "+member.getFederal_ward());
                        mMaritalStatus.setText("Marital Status: "+member.getMarital_status());
                        mAvgIncomeNonFarming.setText("Income on Non-Farming: N "+member.getAvg_income_non_farming());
                        mHouseHoldSize.setText("Household Size: "+member.getHousehold_size());
                        mFIN.setText("FIN: "+member.getFIN());

                        String profile_image = member.getFarmerImage();
                        String id_image = member.getIdentificationImage();
                        Picasso.with(FarmerDetails.this).load(profile_image).into(mfarmerImage);
                        Picasso.with(FarmerDetails.this).load(id_image).into(mIdImage);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                userRef.addListenerForSingleValueEvent(valueEventListener);


            }



        }
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getLocation = new Intent(FarmerDetails.this,GetLocation.class);
                getLocation.putExtra("latitude",latitude);
                getLocation.putExtra("longitude",longitude);
                getLocation.putExtra("position",position);
                startActivity(getLocation);
            }
        });
    }
}
