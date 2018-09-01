package smtagro.donald.com.Admin;

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

import smtagro.donald.com.R;
import smtagro.donald.com.models.AgentModel;

public class ViewAgents extends AppCompatActivity {
    private DatabaseReference agentsDatabase;
    private RecyclerView viewAgentsRecycler;
    private Context context=getBaseContext();
    private FirebaseRecyclerAdapter<AgentModel,ViewAgentsHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agents);

        viewAgentsRecycler = findViewById(R.id.view_agents_recycler);
        viewAgentsRecycler.setHasFixedSize(true);
        viewAgentsRecycler.setLayoutManager(new LinearLayoutManager(context));

        agentsDatabase = FirebaseDatabase.getInstance().getReference().child("Agents");

    }

    @Override
    protected void onStart() {
        super.onStart();
        showAgents();
    }

    public static class ViewAgentsHolder extends RecyclerView.ViewHolder{
        View mView;

        public ViewAgentsHolder(View itemView) {
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
        public void setState(String state){
            TextView tv_id= mView.findViewById(R.id.tv_agent_state);
            tv_id.setText(state);
        }

        private ViewAgentsHolder.ClickListener mClickListener;

        public interface ClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(ViewAgentsHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    public void showAgents(){

        Query firebaseSearchQuery = agentsDatabase.orderByChild("fName");

        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<AgentModel, ViewAgentsHolder>(
                AgentModel.class,
                R.layout.agents_layout,
                ViewAgentsHolder.class,
                firebaseSearchQuery
        ) {

            @Override
            protected void populateViewHolder(ViewAgentsHolder viewHolder,AgentModel model, int position) {
                viewHolder.setName(model.getfName(),model.getlName());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setState(model.getState());
                viewHolder.setLocation(model.getLga());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setId(model.getAgentId());
            }

            @Override
            public ViewAgentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ViewAgentsHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new ViewAgentsHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Toast.makeText(getApplicationContext(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();

                        Intent wholeProfile=new Intent(ViewAgents.this,AgentDetails.class);
                        wholeProfile.putExtra("position",firebaseRecyclerAdapter.getRef(position).getKey());

                        startActivity(wholeProfile);

                    }

                });
                return viewHolder;
            }
        };
        viewAgentsRecycler.setAdapter(firebaseRecyclerAdapter);
    }

}
