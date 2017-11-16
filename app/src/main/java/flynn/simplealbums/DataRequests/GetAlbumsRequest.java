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
import java.net.URI;
import java.util.ArrayList;

import flynn.simplealbums.WebService.WebService;
import flynn.simplealbums.beans.AlbumModel;
import flynn.simplealbums.beans.UserModel;

/**
 * Created by gavin on 2017-11-14.
 */

public class GetAlbumsRequest {

    private Response requestResponse;
    private String AlbumListResponse;

    public void sendRequest(int userid){
        try {
            String url = WebService.BaseURL + "albums";
            Uri RequestUrl = Uri.parse(url)
                    .buildUpon()
                    .appendQueryParameter("userId", Integer.toString(userid))
                    .build();

            requestResponse = WebService.doHttpGet(RequestUrl.toString());

            AlbumListResponse = requestResponse.body().string();


        }catch (IOException e){
            Log.d("IOException:", e.getMessage());
        }

    }

    public boolean isSuccess(){
        return requestResponse.isSuccessful();
    }

    public ArrayList<AlbumModel> getResult(){
        if(AlbumListResponse != null)
        {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = new JSONArray(AlbumListResponse);
                Type listType = new TypeToken<ArrayList<AlbumModel>>(){}.getType();
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
