package concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Created by takirala on 10/17/2016.
 */
public class JavaServer implements Runnable {

    static final int PORT = 1049;
    static ThreadLocal<Socket> incoming = null;

    //ExecutorService e = Executors.n

    public static void main(String[] args) {
        ServerSocket s;
        try {
            s = new ServerSocket(PORT);
            for (; ; ) {
                Socket sa = s.accept();
                incoming = new ThreadLocal<Socket>() {
                    @Override
                    protected Socket initialValue() {
                        return sa;
                    }
                };
                new Thread(new JavaServer()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            talkOnSocket(incoming.get());
        } catch (Exception e) {
        } finally {
            try {
                if (incoming != null) incoming.get().close();
            } catch (IOException ignore) {
            }
        }
    }

    private void talkOnSocket(Socket incoming) throws IOException {
        BufferedReader in =
                new BufferedReader(new
                        InputStreamReader(incoming.getInputStream()));
        PrintWriter out =
                new PrintWriter(incoming.getOutputStream(), true);

        out.println("Hello !Enter BYE to exit.");

        String line = in.readLine();
        while (line != null
                && !line.equals("BYE")
                && !Thread.interrupted()) {
            line = line.trim();
            out.println();
        }
    }

}
