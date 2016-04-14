package giti7083s.angel.command.receiver;


import android.util.Log;
import android.widget.ImageView;

import giti7083s.angel.command.MyCons;

/**
 * Created by angel on 10/04/16.
 */
public class ImageReceiver extends Receiver{
    ImageView imageView;

    public ImageReceiver(ImageView imageView) {
        this.imageView = imageView;
    }

    public void cambiarContraste(float alpha) {
        Log.d(MyCons.LOG, "ImageReceiver.changeAlpha: " + alpha);
        float currentAlpha = imageView.getAlpha();
        currentAlpha += alpha;
        imageView.setAlpha(currentAlpha);
    }
}
