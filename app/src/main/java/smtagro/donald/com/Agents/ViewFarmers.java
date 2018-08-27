package smtagro.donald.com.Agents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import smtagro.donald.com.Admin.FarmerDetails;
import smtagro.donald.com.R;
import smtagro.donald.com.models.FarmersModel;

public class ViewFarmers extends AppCompatActivity {

    private DatabaseReference farmersDatabase;
    private RecyclerView viewFarmersRecycler;
    private Context context=getBaseContext();
    private FirebaseRecyclerAdapter<FarmersModel,ViewFarmerHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_farmers);


        viewFarmersRecycler = findViewById(R.id.view_farmer_recycler);
        viewFarmersRecycler.setHasFixedSize(true);
        viewFarmersRecycler.setLayoutManager(new LinearLayoutManager(context));

        farmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");
    }

    public static class ViewFarmerHolder extends RecyclerView.ViewHolder{
        View mView;

        public ViewFarmerHolder(View itemView) {
            super(itemView);
            mView=itemView;

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view, getAdapterPosition());

                }
            });

        }
        public void setName(String fname){
            TextView name= mView.findViewById(R.id.tv_farmers_name);
            name.setText(fname);

        }
        public void setPhone(String phone){
            TextView phones = mView.findViewById(R.id.tv_farmers_phone);
            phones.setText(phone);
        }
        public void setLocation(String location){
            TextView locations= mView.findViewById(R.id.tv_farmer_lga);
            locations.setText(location);
        }
        public void setAddress(String address){
            TextView tvaddress= mView.findViewById(R.id.tv_farmer_address);
            tvaddress.setText(address);
        }

        public void setFin(String fin){
            TextView tv_id= mView.findViewById(R.id.tv_farmer_fin);
            tv_id.setText(fin);
        }

        private ViewFarmerHolder.ClickListener mClickListener;

        public interface ClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(ViewFarmerHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    public void showFarmers(){

        Query firebaseSearchQuery = farmersDatabase.orderByChild("names");

        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<FarmersModel, ViewFarmerHolder>(
                FarmersModel.class,
                R.layout.farmers_layout,
                ViewFarmerHolder.class,
                firebaseSearchQuery
        ) {

            @Override
            protected void populateViewHolder(ViewFarmerHolder viewHolder, FarmersModel model, int position) {
                viewHolder.setName(model.getNames());
                viewHolder.setPhone(model.getPhone_number());
                viewHolder.setLocation(model.getLga());
                viewHolder.setAddress(model.getCenter_name());
                viewHolder.setFin(model.getFIN());
            }

            @Override
            public ViewFarmerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ViewFarmerHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new ViewFarmerHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Toast.makeText(getApplicationContext(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();

                        Intent wholeProfile=new Intent(ViewFarmers.this,FarmerDetails.class);
                        wholeProfile.putExtra("position",firebaseRecyclerAdapter.getRef(position).getKey());

                        startActivity(wholeProfile);

                    }

                });
                return viewHolder;
            }
        };
        viewFarmersRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showFarmers();
    }
}
