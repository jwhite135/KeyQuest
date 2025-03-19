package com.model;

import java.util.ArrayList;

public class PostDatabase {
    private static PostDatabase postDatabase;
    private ArrayList<Post> posts;

    private PostDatabase() {
        this.posts = new ArrayList<Post>();
    }

    public static PostDatabase getInstance() {
        if (postDatabase == null) {
            postDatabase = new PostDatabase();
        }
        return postDatabase;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<Post> searchByName(String title) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                result.add(post);
            }
        }
        return result;
    }

    public ArrayList<Post> searchBySong(String song) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getSong().getName().equalsIgnoreCase(song)) {
                result.add(post);
            }
        }
        return result;
    }

    public ArrayList<Post> sortByMostRecent() {
        posts.sort( (a,b) -> b.getDate().compareTo(a.getDate()) );
        return posts;
    }

    public ArrayList<Post> sortByMostLiked() {
        posts.sort( (a,b) -> Integer.compare(a.getFavorites(), b.getFavorites()) );
        return posts;
    }

    public boolean addPost(Post post) {
        return posts.add(post);
    }

    public void save() {
        DataWriter.savePosts();
    } 

}
