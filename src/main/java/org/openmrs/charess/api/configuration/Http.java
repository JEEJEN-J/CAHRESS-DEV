package org.openmrs.charess.api.configuration;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Http {

    public static HttpURLConnection httpURLConnection = null;

    /**
     * USER AUTHENTIFICATION
     */
    public static HttpURLConnection getHttpConnection(String url, String type) {
         httpURLConnection = null;
        try {
            URL uri = new URL(url);
            httpURLConnection = (HttpURLConnection) uri.openConnection();
            httpURLConnection.setRequestMethod(type); //type: POST, PUT, DELETE, GET
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(60000); //60 secs
            httpURLConnection.setReadTimeout(60000); //60 secs
            httpURLConnection.setRequestProperty("Accept-Encoding", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
        } catch (Exception e) {
            e.getSuppressed();
        }
        return httpURLConnection;
    }


    public static HttpURLConnection deconnection(){
        try {
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpURLConnection;
    }


    /**
     * GET BY ALL SERVICES
     */
    public static List<?> getObject(HttpURLConnection httpURLConnection) {
        InputStreamReader inputStreamReader = null;
        List<Object> objects = null;
        BufferedReader bufferedReader = null;
        String output = "";
        try {
            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            objects = new ArrayList<>();
            while ((output = bufferedReader.readLine()) != null)
                objects.add(output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return objects;
    }


    /**
     * POST AND PUT BY ALL SERVICES
     */
    public static List<?> postObject(HttpURLConnection httpURLConnection, String reqbody) {
        OutputStream outputStream = null;
        List<Object> objects = null;
        try {
            outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            byte[] input = reqbody.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
            objects = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
                String responseLine = "";
                while ((responseLine = br.readLine()) != null)
                    objects.add(responseLine);
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
//                    return Collections.singletonList("BAD_REQUEST");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }


}
