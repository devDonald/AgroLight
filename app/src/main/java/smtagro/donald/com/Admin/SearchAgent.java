package smtagro.donald.com.Admin;

import android.content.Context;
import android.content.Intent;
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
import smtagro.donald.com.models.AgentModel;

public class SearchAgent extends AppCompatActivity {
    private RecyclerView agentSearchRecycler;
    private DatabaseReference agentDatabase;
    private Context context=getBaseContext();
    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private FirebaseRecyclerAdapter<AgentModel,SearchHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_agent);

        agentSearchRecycler= findViewById(R.id.agent_search_recycler);
        agentDatabase= FirebaseDatabase.getInstance().getReference().child("Agents");
        agentSearchRecycler.setHasFixedSize(true);
        agentSearchRecycler.setLayoutManager(new LinearLayoutManager(context));
        mSearchField = findViewById(R.id.agent_search_field);
        mSearchBtn = findViewById(R.id.agent_search_btn);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                showAgents(searchText);
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
        public void setName(String fname,String lname){
            TextView name= mView.findViewById(R.id.tv_agent_name);
            name.setText(fname+" "+lname);

        }
        public void setPhone(String phone){
            TextView phones = mView.findViewById(R.id.tv_agent_phone);
            phones.setText(phone);
        }
        public void setLocation(String location){
            TextView locations= mView.findViewById(R.id.tv_agent_lga);
            locations.setText(location);
        }
        public void setAddress(String address){
            TextView tvaddress= mView.findViewById(R.id.tv_agent_address);
            tvaddress.setText(address);
        }

        public void setId(String id){
            TextView tv_id= mView.findViewById(R.id.tv_agent_id);
            tv_id.setText(id);
        }

        private SearchHolder.ClickListener mClickListener;

        public interface ClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(SearchHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    public void showAgents(String searchText){

        Query firebaseSearchQuery = agentDatabase.orderByChild("fName").startAt(searchText).endAt(searchText + "\uf8ff");
        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<AgentModel, SearchHolder>(
                AgentModel.class,
                R.layout.agents_layout,
                SearchHolder.class,
                firebaseSearchQuery
        ) {

            @Override
            protected void populateViewHolder(SearchHolder viewHolder, AgentModel model, int position) {
                viewHolder.setName(model.getfName(),model.getlName());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setLocation(model.getLga());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setId(model.getAgentId());
            }

            @Override
            public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                SearchHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new SearchHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Toast.makeText(getApplicationContext(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();

                        Intent wholeProfile=new Intent(SearchAgent.this,AgentDetails.class);
                        wholeProfile.putExtra("position",firebaseRecyclerAdapter.getRef(position).getKey());

                        startActivity(wholeProfile);

                    }

                });
                return viewHolder;
            }
        };
        agentSearchRecycler.setAdapter(firebaseRecyclerAdapter);
    }
}
