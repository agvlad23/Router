package router.client;

import bot.model.User;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class RestClient {
    public static void addUser(User user){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://restUrl").build());

/*        System.out.println(service. path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
        ClientResponse response = webResource.path("restPath").path("resourcePath").
                type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, user);
        System.out.println("Response " + response.getEntity(String.class));*/


    }
    public static User getUser(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/").build());

        System.out.println(service. path("Rest_Service_war_exploded").path("user").accept(MediaType.APPLICATION_JSON).get(String.class));
        return null;
    }
}
