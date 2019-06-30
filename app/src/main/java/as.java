import android.os.AsyncTask;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class as extends AsyncTask<String,Void,String> {
    Socket s;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    @Override
    protected String doInBackground(String... strings) {
        try {
            System.out.println("salam");
            s = new Socket("10,0,2,2",6800);
            objectOutputStream = new ObjectOutputStream(s.getOutputStream());
           objectInputStream= new ObjectInputStream(s.getInputStream());
           objectOutputStream.writeObject("hello");
           objectOutputStream.flush();
           objectOutputStream.close();
           objectInputStream.close();
           s.close();
        }
        catch (Exception e)
        {

        }
        return null;
    }

}
