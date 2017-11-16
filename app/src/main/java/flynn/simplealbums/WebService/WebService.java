package flynn.simplealbums.WebService;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class WebService {

    public final static String BaseURL = "https://jsonplaceholder.typicode.com/";


    public static Response doHttpGet(String url) throws IOException {

        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(60, TimeUnit.SECONDS);

        if(url == null || url.isEmpty()){
            throw new IOException("Url can not be empty");
        }


        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .build();

        return client.newCall(request).execute();
    }

}
