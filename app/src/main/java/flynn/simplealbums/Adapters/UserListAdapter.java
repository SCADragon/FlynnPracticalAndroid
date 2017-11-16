package flynn.simplealbums.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import flynn.simplealbums.AlbumActivity;
import flynn.simplealbums.R;
import flynn.simplealbums.beans.UserModel;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserDataViewHolder> {

    private ArrayList<UserModel> userModelArrayList;
    private Context context;

    public UserListAdapter(ArrayList<UserModel> userModelArrayList, Context context) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
    }

    @Override
    public UserListAdapter.UserDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserDataViewHolder(LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(UserListAdapter.UserDataViewHolder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);
        holder.userName.setText(userModel.getName());
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class UserDataViewHolder extends RecyclerView.ViewHolder{

        private TextView userName;
        private LinearLayout user_overall_layout;

        public UserDataViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            user_overall_layout = (LinearLayout) itemView.findViewById(R.id.user_overall_layout);

            user_overall_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer userID = userModelArrayList.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context,AlbumActivity.class);
                    intent.putExtra("userid",userID);
                    context.startActivity(intent);
                }
            });

        }
    }


}
