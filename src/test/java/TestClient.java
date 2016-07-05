import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hanminggui on 7/5/2016.
 */
public class TestClient {
    public static void main(String[] args){
        try {
            Socket so = new Socket("127.0.0.1", 6666);
            PrintWriter pw = new PrintWriter(so.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
            pw.write("str1");
            pw.write("str2");
            pw.write("str3");
            pw.write("han");
            pw.write("83832391027a1f2f4d46ef882ff3a47d");
            System.out.println(br.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
