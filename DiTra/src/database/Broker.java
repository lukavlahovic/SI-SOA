package database;

import appFramework.Repository;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import tree.treeModel.Entitet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Broker implements Repository {
    @Override
    public void add(Entitet entitet, String[] redKojiSeDodaje) throws SQLException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/api/teski");

        String json = "{ \"entitet\":\"" + entitet.getName() + "\",\"atributi\":{";
        for(int i=0;i<entitet.getAtribut().size();i++){
            json += "\"" + entitet.getAtribut().get(i) + "\":" + "\"" + redKojiSeDodaje[i] + "\"";
            if (i<entitet.getAtribut().size()-1) json+=",";
        }
        json += "}}";
        System.out.println(json);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {
            CloseableHttpResponse response = client.execute(httpPost);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Entitet entitet, String[] redKojiSeDodaje) throws SQLException {

    }

    @Override
    public void update(Entitet entitet, String[] redKojiSeDodaje, String staraVrednost) throws SQLException {

    }
}
