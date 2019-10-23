package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaDrm;
import android.os.AsyncTask;


import com.example.jokesandroidlib.JokesActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


class EndpointsAsyncTask<OnEventListener> extends AsyncTask<Context, Void, String> {
    public static MyApi myApiService = null;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private OnEventListener  mCallBack;
    String text;
    public Exception mException;


    public EndpointsAsyncTask(Context context, OnEventListener callback) {
        mCallBack = callback;
        mContext = context;
    }


    @Override
    protected String doInBackground(Context...params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest -> abstractGoogleClientRequest.setDisableGZipContent(true));
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = params[0];

        try {
            return myApiService.getRandomJokeService().execute().getData();
        } catch (Exception e) {
            mException=e;
        }
    return null;
    }

    @Override
    protected void onPostExecute(String result) {
        final Intent intent = new Intent(mContext, JokesActivity.class);
        if(mCallBack != null){
            if(mException != null){
                intent.putExtra("gce_result",result);
        mContext.startActivity(intent);
    }
            else {
                intent.putExtra("Error",mException);
                mContext.startActivity(intent);
            }
        }
    }
}

