package router.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import router.model.User;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.util.List;


public class RestClient {
    public static void addUser(User user){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/").build());

        //System.out.println(service. path("Rest_Service_war_exploded").path("user").accept(MediaType.APPLICATION_JSON).get(String.class));
        ClientResponse response = service.path("Rest_Service_war_exploded").path("user").
                type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, user);
        System.out.println("Response " + response.getEntity(String.class));

    }
    public static List<User> getUser(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/").build());

        var k2=service.path("Rest_Service_war_exploded").path("user").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>(){});
        return k2;
    }

    public static void deleteUser(String telegramId){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/").build());

        service.path("Rest_Service_war_exploded").path("user").accept(MediaType.APPLICATION_JSON).delete(telegramId);

    }
}
