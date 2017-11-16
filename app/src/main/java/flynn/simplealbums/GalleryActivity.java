package flynn.simplealbums;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import flynn.simplealbums.Adapters.GalleryAdapter;
import flynn.simplealbums.DataRequests.GetPhotoRequest;
import flynn.simplealbums.beans.PhotoModel;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView gallary;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (NullPointerException e){
            Log.d("Simple Album", "Exception:" + e.getMessage());
        }

        Intent intent = getIntent();
        int albumid = intent.getIntExtra("album",1);

        gallary = (RecyclerView) findViewById(R.id.gallery);
        gallary.setLayoutManager(new GridLayoutManager(this, 3));

        mContext = this;

        new GetGalleryData(albumid).execute();

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

    private class GetGalleryData extends
            AsyncTask<Void, Void, Boolean> {
        GetPhotoRequest getPhotoRequest = new GetPhotoRequest();

        private GalleryAdapter galleryAdapter;
        private int albumid;

        public GetGalleryData(int albumid) {
            this.albumid = albumid;
        }

        @Override
        protected Boolean doInBackground(
                Void... params) {
            getPhotoRequest.sendRequest(albumid);

            return getPhotoRequest.isSuccess();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                ArrayList<PhotoModel> photos = getPhotoRequest.getResult();
                galleryAdapter = new GalleryAdapter(photos, mContext);
                gallary.setAdapter(galleryAdapter);
            }
        }

    }
}
