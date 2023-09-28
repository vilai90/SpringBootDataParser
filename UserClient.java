package com.example.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserClient {
	private int userListTotal;

	public static void main(String[] args) {
		try {
			UserClient userClient = new UserClient();
			URL url = new URL("https://reqres.in/api/users?page=1");
			for (User user : userClient.retrieveUserList(url)){
				System.out.println("First Name:" + user.getFirstName());
				System.out.println("Last Name:" + user.getLastName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<User> retrieveUserList(URL url) throws IOException {
		List<User> userList = new ArrayList<>();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Gson gson = new Gson();
			Response jsonResponse = gson.fromJson(response.toString(), Response.class); //map response to response class
			for (User user: jsonResponse.getData()){  //extract users into user list
				userList.add(user);
			}
			userListTotal = jsonResponse.getPerPage();
			System.out.println("Total Number of Users:" + userListTotal);
			return userList;
		} else {
			System.out.println("GET request did not work.");
			return userList;
		}
	}

	public int getUserListTotal() {
		return userListTotal;
	}
}