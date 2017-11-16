package flynn.simplealbums;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import flynn.simplealbums.Adapters.UserListAdapter;
import flynn.simplealbums.DataRequests.GetUserRequest;
import flynn.simplealbums.beans.UserModel;

public class UserActivity extends AppCompatActivity {

    RecyclerView UserList;

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        UserList = (RecyclerView) findViewById(R.id.UserList);

        mContext = this;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        UserList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(UserList.getContext(),
                DividerItemDecoration.VERTICAL);
        UserList.addItemDecoration(dividerItemDecoration);
        try {
            getSupportActionBar().setTitle(getString(R.string.select_friend));
        }catch (NullPointerException e){
            Log.d("Simple Album", "Exception:" + e.getMessage());
        }

        new GetUserData().execute();
    }

    private class GetUserData extends
            AsyncTask<Void, Void, Boolean> {
        GetUserRequest userRequest = new GetUserRequest();

        private UserListAdapter userListAdapter;

        @Override
        protected Boolean doInBackground(
                Void... params) {
            userRequest.sendRequest();

            return userRequest.isSuccess();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                ArrayList<UserModel> userModels = userRequest.getResult();
                userListAdapter = new UserListAdapter(userModels, mContext);
                UserList.setAdapter(userListAdapter);
            }
        }

    }


}
