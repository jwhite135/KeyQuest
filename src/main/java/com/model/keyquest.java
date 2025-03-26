package com.model;

import java.util.ArrayList;

public class keyquest {
    public static void main(String[] args) {
        PostDatabase postDatabase = PostDatabase.getInstance();
        ArrayList<Post> posts = postDatabase.getPosts();
        for(Post post : posts) {
            System.out.println(post.getTitle());
            System.out.println(post.getBody());
            System.out.println(post.getDate());
            System.out.println(post.getFavorites());
            ArrayList<Comment> comments = post.getComments();
            for(Comment comment : comments) {
                System.out.println(comment.getBody());
                System.out.println(comment.getDate());
            }
            System.out.println(post.getIsPrivate());
        }
    }
}