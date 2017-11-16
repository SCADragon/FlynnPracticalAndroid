package flynn.simplealbums.DataRequests;

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
import flynn.simplealbums.beans.UserModel;

public class GetUserRequest {

    private Response requestResponse;
    private String UserListResponse;

    public void sendRequest(){
        try {
            String url = WebService.BaseURL + "users";

            requestResponse = WebService.doHttpGet(url);

            UserListResponse = requestResponse.body().string();


        }catch (IOException e){
            Log.d("IOException:", e.getMessage());
        }

    }

    public boolean isSuccess(){
        return requestResponse.isSuccessful();
    }

    public ArrayList<UserModel> getResult(){
        if(UserListResponse != null)
        {
            Gson gson = new Gson();
            try {
                JSONArray jsonArray = new JSONArray(UserListResponse);
                Type listType = new TypeToken<ArrayList<UserModel>>(){}.getType();
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
