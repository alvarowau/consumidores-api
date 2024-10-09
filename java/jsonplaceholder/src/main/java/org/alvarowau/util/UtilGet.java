package org.alvarowau.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UtilGet {

    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static Gson gson = new Gson();

    public static HttpRequest getRequest(String uri) {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(URL + uri))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            System.out.println("Problema al construir la URI en el método get");
            e.printStackTrace();
        }
        return request;
    }

    public static HttpResponse<String> getResponse(HttpRequest request, HttpClient client) {
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            System.out.println("Error al obtener la respuesta");
            e.printStackTrace();
        }

        return response; // Asegúrate de retornar la respuesta
    }

}
