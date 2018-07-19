package com.example.goku.asynctask;


import android.os.Environment;
import java.io.File;
import java.io.FileFilter;

public class GetImage {
    private static final String PATH = Environment.getExternalStorageDirectory().getPath().toString();
    private static final String PATH_CAMERA = PATH + "/DCIM/Camera";

    protected File[] getAllImage(){
        File file = new File(PATH_CAMERA);
        File[] images = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getAbsolutePath().endsWith(".png")
                        || file.getAbsolutePath().endsWith(".jpg")
                        || file.getAbsolutePath().endsWith("jpeg");
            }
        });
        return images;
    }
}
