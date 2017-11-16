package flynn.simplealbums;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import flynn.simplealbums.Adapters.AlbumListAdapter;
import flynn.simplealbums.DataRequests.GetAlbumsRequest;
import flynn.simplealbums.beans.AlbumModel;

public class AlbumActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView AlbumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        mContext = this;
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (NullPointerException e){
            Log.d("Simple Album", "Exception:" + e.getMessage());
        }

        AlbumList = (RecyclerView) findViewById(R.id.AlbumList);
        Intent intent = getIntent();
        int userid = intent.getIntExtra("userid",1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        AlbumList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(AlbumList.getContext(),
                DividerItemDecoration.VERTICAL);
        AlbumList.addItemDecoration(dividerItemDecoration);

        new GetAlbumData(userid).execute();
    }
    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private class GetAlbumData extends
            AsyncTask<Void, Void, Boolean> {
        GetAlbumsRequest getAlbumData = new GetAlbumsRequest();

        private AlbumListAdapter albumListAdapter;
        private int userid;

        public GetAlbumData(int userid) {
            this.userid = userid;
        }

        @Override
        protected Boolean doInBackground(
                Void... params) {
            getAlbumData.sendRequest(userid);

            return getAlbumData.isSuccess();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                ArrayList<AlbumModel> albumModels = getAlbumData.getResult();
                albumListAdapter = new AlbumListAdapter(albumModels, mContext);
                AlbumList.setAdapter(albumListAdapter);
            }
        }

    }


}
