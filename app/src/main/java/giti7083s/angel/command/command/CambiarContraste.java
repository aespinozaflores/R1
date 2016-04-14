package giti7083s.angel.command.command;

//librerias que permiten escribir un objeto y luego llamarlo en otras clases.
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import giti7083s.angel.command.receiver.ImageReceiver;
import giti7083s.angel.command.receiver.Receiver;

/**
 * Created by angel on 10/04/16.
 */
//Esta clase es el comando concreto,
public class CambiarContraste implements Command,
        Parcelable,//for transfer command between 2 activities
        Serializable //for persistent command to file

{


        transient ImageReceiver imageReceiver;
        float alpha;

        public CambiarContraste(ImageReceiver imageReceiver, float alpha) {
        this.imageReceiver = imageReceiver;
        this.alpha = alpha;
    }

        @Override
        public void execute() {
        imageReceiver.cambiarContraste(alpha);
    }

        @Override
        public void undo() {
        imageReceiver.cambiarContraste(-alpha);
    }

        @Override
        public void redo() {
        imageReceiver.cambiarContraste(alpha);
    }

        @Override
        public void setReceiver(Receiver receiver) {
        this.imageReceiver = (ImageReceiver) receiver;
    }

        @Override
        public String toString() {
        return alpha > 0 ? "Subir Contraste" : "Bajar contraste";
    }

        protected CambiarContraste(Parcel in) {
        alpha = in.readFloat();
    }

        public static final Creator<CambiarContraste> CREATOR = new Creator<CambiarContraste>() {
            @Override
            public CambiarContraste createFromParcel(Parcel in) {
                return new CambiarContraste(in);
            }

            @Override
            public CambiarContraste[] newArray(int size) {
                return new CambiarContraste[size];
            }
        };

        @Override
        public int describeContents() {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(alpha);
    }


    }
