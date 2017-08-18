package org.basesource.reservoir.impl;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.IntStream;

public class HttpStreamer extends BaseStreamer {


    public HttpStreamer(String url) {
        super(url);

        assert url != null && url.length() > 8 : "HTTP URL is not valid.";
    }

    @Override
    public IntStream stream() {

        URL myUrl = null;
        HttpsURLConnection conn =null;
        StringBuilder result = null;

        try {
            myUrl = new URL(this.input.toString());
            conn = (HttpsURLConnection) myUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);

            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            result = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.chars();
    }
}
