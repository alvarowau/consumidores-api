package org.alvarowau;

import org.alvarowau.consumer.ApiConsumerPost;
import org.alvarowau.consumer.ApiConsumerUsers;
import org.alvarowau.model.Post;
import org.alvarowau.model.User;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        ApiConsumerPost apiPost = new ApiConsumerPost(client);
        ApiConsumerUsers apiUser = new ApiConsumerUsers(client);

        List<Post> posts = apiPost.getAllPosts();

//        // Imprimir los posts
//        for (Post post : posts) {
//            System.out.println("ID: " + post.id() + ", Title: " + post.title());
//        }

        Optional<Post> post = apiPost.getPostById(3);
        if (post.isPresent()) {
            System.out.println(post.get());
        }else{
            System.out.println("No post found");
        }

        Optional<User> user = apiUser.getUserById(1);
        if (user.isPresent()) {
            System.out.println(user.get());
        }else{
            System.out.println("No user found");
        }

        List<User> users = apiUser.getAllUsers();
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
