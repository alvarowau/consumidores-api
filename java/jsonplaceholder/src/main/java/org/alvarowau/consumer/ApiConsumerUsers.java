package org.alvarowau.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.alvarowau.model.User;
import org.alvarowau.util.UtilGet;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApiConsumerUsers {

    private final HttpClient client;

    private final Gson gson;

    public ApiConsumerUsers(HttpClient client) {
        this.client = client;
        gson = new Gson();
    }

    public Optional<User> getUserById(int id) {
        String uri = "/users/" + id;
        HttpRequest request = UtilGet.getRequest(uri);
        if(request != null) {
            HttpResponse<String> response = UtilGet.getResponse(request,client);
            if(response != null && response.statusCode() == 200) {
                return Optional.of(gson.fromJson(response.body(), User.class));
            }
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        String uri = "/users";
        HttpRequest request = UtilGet.getRequest(uri);
        if(request != null) {
            HttpResponse<String> response = UtilGet.getResponse(request,client);
            if(response != null && response.statusCode() == 200) {
                return gson.fromJson(response.body(), new TypeToken<List<User>>() {});
            }
        }
        return List.of();
    }
}
