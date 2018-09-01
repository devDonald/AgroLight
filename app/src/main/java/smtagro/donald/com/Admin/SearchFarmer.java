package smtagro.donald.com.Admin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import smtagro.donald.com.R;
import smtagro.donald.com.models.FarmersModel;

public class SearchFarmer extends AppCompatActivity {
    private RecyclerView farmerSearchRecycler;
    private DatabaseReference farmerDatabase;
    private Context context=getBaseContext();
    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private FirebaseRecyclerAdapter<FarmersModel, SearchHolder> firebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_farmer);

        farmerSearchRecycler= findViewById(R.id.farmer_search_recycler);
        farmerDatabase= FirebaseDatabase.getInstance().getReference().child("Farmer");
        farmerSearchRecycler.setHasFixedSize(true);
        farmerSearchRecycler.setLayoutManager(new LinearLayoutManager(context));
        mSearchField = findViewById(R.id.farmer_search_field);
        mSearchBtn = findViewById(R.id.farmer_search_btn);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                showFarmer(searchText);
            }
        });
    }

    public static class SearchHolder extends RecyclerView.ViewHolder{
        View mView;

        public SearchHolder(View itemView) {
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

        public void setState(String state){
            TextView tv_id= mView.findViewById(R.id.tv_farmer_state);
            tv_id.setText(state);
        }

        private SearchHolder.ClickListener mClickListener;

        public interface ClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(SearchHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    public void showFarmer(String searchText){

        Query firebaseSearchQuery = farmerDatabase.orderByChild("fin").startAt(searchText).endAt(searchText + "\uf8ff");
        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<FarmersModel, SearchHolder>(
                FarmersModel.class,
                R.layout.farmers_layout,
                SearchHolder.class,
                firebaseSearchQuery
        ) {

            @Override
            protected void populateViewHolder(SearchHolder viewHolder, FarmersModel model, int position) {
                viewHolder.setName(model.getNames());
                viewHolder.setPhone(model.getPhone_number());
                viewHolder.setLocation(model.getLga());
                viewHolder.setAddress(model.getFarmLocation());
                viewHolder.setFin(model.getFIN());
                viewHolder.setState(model.getState());
            }

            @Override
            public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                SearchHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new SearchHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Toast.makeText(getApplicationContext(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
//
//                        Intent wholeProfile=new Intent(SearchFarmer.this,FarmerDetails.class);
//                        wholeProfile.putExtra("position",firebaseRecyclerAdapter.getRef(position).getKey());
//
//                        startActivity(wholeProfile);

                    }

                });
                return viewHolder;
            }
        };
        farmerSearchRecycler.setAdapter(firebaseRecyclerAdapter);
    }
}
