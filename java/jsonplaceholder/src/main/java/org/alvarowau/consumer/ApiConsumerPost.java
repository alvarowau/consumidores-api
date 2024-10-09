package org.alvarowau.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.alvarowau.model.Post;
import org.alvarowau.util.UtilGet;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class ApiConsumerPost {

    private final HttpClient client;

    private final Gson gson;

    public ApiConsumerPost(HttpClient client) {
        this.client = client;
        this.gson = new Gson(); // Mover la creación de Gson aquí
    }

    public Optional<Post> getPostById(int id) {
        String uri = "/posts/" + id;
        HttpRequest request = UtilGet.getRequest(uri);
        if (request != null) {
            HttpResponse<String> response = UtilGet.getResponse(request, client);
            if (response != null && response.statusCode() == 200) {
                return Optional.of(gson.fromJson(response.body(), Post.class));
            }
        }
        return Optional.empty();
    }

    public List<Post> getAllPosts() {
        String uri = "/posts";
        HttpRequest request = UtilGet.getRequest(uri);

        if (request != null) {
            HttpResponse<String> response = UtilGet.getResponse(request, client);
            if (response != null && response.statusCode() == 200) {
                // Deserializar la respuesta JSON a una lista de posts
                return gson.fromJson(response.body(), new TypeToken<List<Post>>() {
                }.getType());
            }
        }

        return List.of(); // Retorna una lista vacía si hay un problema
    }
}
