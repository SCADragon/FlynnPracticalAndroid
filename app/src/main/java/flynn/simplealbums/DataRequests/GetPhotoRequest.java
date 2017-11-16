package flynn.simplealbums.DataRequests;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import flynn.simplealbums.WebService.WebService;
import flynn.simplealbums.beans.PhotoModel;


public class GetPhotoRequest {

    private Response requestResponse;
    private String PhotoListResponse;

    public void sendRequest(int albumid){
        try {
            String url = WebService.BaseURL + "photos";
            Uri RequestUrl = Uri.parse(url)
                    .buildUpon()
                    .appendQueryParameter("albumId", Integer.toString(albumid))
                    .build();

            requestResponse = WebService.doHttpGet(RequestUrl.toString());

            PhotoListResponse = requestResponse.body().string();


        }catch (IOException e){
            Log.d("IOException:", e.getMessage());
        }

    }

    public boolean isSuccess(){
        return requestResponse.isSuccessful();
    }

    public ArrayList<PhotoModel> getResult(){
        if(PhotoListResponse != null)
        {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = new JSONArray(PhotoListResponse);
                Type listType = new TypeToken<ArrayList<PhotoModel>>(){}.getType();
                return gson.fromJson(jsonArray.toString(), listType);
            }catch (JSONException e){
                Log.d("JSONException:", e.getMessage());
                return null;
            }

        }else{
            return  null;
        }
    }
}
