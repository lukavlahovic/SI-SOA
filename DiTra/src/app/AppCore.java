/***********************************************************************
 * Module:  AppCore.java
 * Author:  User
 * Purpose: Defines the Class AppCore
 ***********************************************************************/

package app;


import java.io.IOException;
import java.util.*;

import appFramework.AppFramework;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import tree.treeModel.TreeModelImplementacion;

public class AppCore extends AppFramework {
	public static void main(String[] args) throws IOException, ProtocolException {
		AppFramework af = new AppCore();
		af.create();// kreira TreeModelImplementacion i GuiImplementation
		af.getGui().createGui(af.getTree()); //kreira MainFrame i povezuje View sa Modelom
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/login");
		String json = "{\"username\":\"teskiKlijent3\",\"password\":\"1234\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = client.execute(httpPost);
		client.close();
		Constants.setToken(response.getHeader("Authorization").toString().substring(15));
		System.out.println(Constants.token);

	
	}
}