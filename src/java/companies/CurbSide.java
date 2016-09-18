package java.companies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Lavenger on 9/9/2016.
 */
public class CurbSide {

    public static final String ROOT = "http://challenge.shopcurbside.com/";

    public static void main(String[] args) {
        CurbSide cs = new CurbSide();
        cs.run();
    }

    private void run() {
        ArrayList<String> crawled = new ArrayList<>();
        StringBuffer secret = new StringBuffer();

        crawl(crawled, secret);
    }

    private void crawl(ArrayList<String> crawled, StringBuffer secret) {


    }

    void getResponse() {

    }

    String getSession() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(ROOT + "get-session").openConnection();
        // Always assume it will return a token.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return in.readLine();
        }
    }

}
