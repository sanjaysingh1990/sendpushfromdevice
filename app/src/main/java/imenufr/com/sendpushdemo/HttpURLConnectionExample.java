package imenufr.com.sendpushdemo;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "https://fcm.googleapis.com/fcm/send";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	public String sendPost() throws Exception {
		String authKey ="AIzaSyD3VGRPj9968kun-pmJozO3zQJLRcSX3dY"; // You FCM AUTH key

		String url = "https://fcm.googleapis.com/fcm/send";
		URL obj = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		//notification object
		JSONObject notification = new JSONObject();
		notification.put("title", "FCM Notificatoin Title"); // Notification title
		notification.put("text", "Hello First Test notification"); // Notification body

        //main object
		JSONObject data = new JSONObject();
         //add notificaiton body
		data.put("notification",notification);
		//add receipeint
		data.put("to","ekIcft8MTXI:APA91bGDxG2qlznVXEWxvNB9qorL4B1poZVcBcA9_kVA7s8GAkAIx-Iuf5IA3L7Hbn-NjUBbdscEyK-g_HMjInHMJojtd5BmsVMQxLKzv9cVRAgFIDNGuz8Ok7vNRESaoXZA-C87ahR3");
		JSONObject info = new JSONObject();
		info.put("title", "FCM Notificatoin Title"); // Notification title
		info.put("body", "Hello First Test notification"); // Notification body
		data.put("data", info);



		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data.toString());
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		Log.e("code","Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return (response.toString());

	}

}