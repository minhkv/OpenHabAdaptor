/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openhab.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.www.http.HttpClient;

/**
 *
 * @author khongvanminh
 */
public class OpenHabRest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String USER_AGENT = "Mozilla/5.0";

            String GET_URL = "http://localhost:9090/SpringMVCExample";

            String POST_URL = "http://localhost:8088/rest/items/Light_FF_Bath_Ceiling";

            String POST_PARAMS = "userName=Pankaj";
            
            URL obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.addRequestProperty("Content-Type", "Text");

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(OpenHabRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenHabRest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
