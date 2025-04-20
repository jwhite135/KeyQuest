package com.model;

import java.util.ArrayList;
/**
 * keyquest class that holds the main method to run the program
 * This class is used to test the PostDatabase class and its methods
 * It currently prints out all the posts and their comments
 * @author Ian Attmore
 */
public class keyquest {
    public static void main(String[] args) {
        UserDatabase users = UserDatabase.getInstance();
        SongDatabase songs = SongDatabase.getInstance();
        PostDatabase posts = PostDatabase.getInstance();
        users.populate();
        posts.populate();
        ArrayList<User> userList = users.getUserList();
        for (User user : userList) {
            System.out.println("User: " + user.getUsername());
            System.out.println("User Favorite Songs: ");
            for (Song song : user.getFavoriteSongs()) {
                System.out.println(song.getName());
            }
            System.out.println("User Favorited Posts: ");
            for (Post post : user.getFavoritePosts()) {
                System.out.println(post.getTitle());
            }
            System.out.println("User Friends: ");
            for (User friend : user.getFriends()) {
                System.out.println(friend.getUsername());
            }
            System.out.println("User Posts: ");
            for (Post post : user.getAuthoredPosts()) {
                System.out.println(post.getTitle());
            }
        }
        ArrayList<Post> postList = posts.getPostList();
        for (Post post : postList) {
            System.out.println("Post Title: " + post.getTitle());
            System.out.println("Post Song Title: " + post.getSong().getName());
            System.out.println("Post Author Username:" + post.getAuthor().getUsername());
        }
    }
}