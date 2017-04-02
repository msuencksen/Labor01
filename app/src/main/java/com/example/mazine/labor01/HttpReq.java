
package com.example.mazine.labor01;

/**
 * ##todo: simplify RequestProperties v
 */

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpReq
{

    public HttpReq(){ }

    public static String getResponse(String link) {

        Log.d(MainActivity.TAG, "link: " + link );

        String response="";

        HttpURLConnection connection=null;
        try{
            URL url=new URL(link);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("HOST", "example.com");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:16.0) Gecko/20100101 Firefox/16.0");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line = "";
            while((line=in.readLine())!=null)
                response+=(line+"\n");
            in.close();
        }catch(Exception e) {
            Log.d(MainActivity.TAG, "ERROR !!! " + e.toString());
            response = "";
        }
        Log.v(MainActivity.TAG, "response: " + response );
        return response;
    }
}
