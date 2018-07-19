package com.example.goku.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.File;

public class MyAsyncTask extends AsyncTask<String,Void,File[]> {
   private Activity mActivity;

    private GetImage mGetImage;
   private ScreenActivity mScreenActivity=new ScreenActivity();


    public MyAsyncTask(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mActivity, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected File[] doInBackground(String... strings) {
        mGetImage=new GetImage();
        return mGetImage.getAllImage();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mScreenActivity.getData();
    }

    @Override
    protected void onPostExecute(File[] files) {
        super.onPostExecute(files);
        Toast.makeText(mActivity, " Finished", Toast.LENGTH_SHORT).show();
    }
}

