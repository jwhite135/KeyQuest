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
        /*
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getName().equalsIgnoreCase(title)) {
                result.add(post);
            }
        }
        return result;
        */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Post> searchByArist(String author) {
        /*
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getArtist().equalsIgnoreCase(author)) {
                result.add(post);
            }
        }
        return result;
        */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Post> searchByDifficulty(int difficulty) {
        /*
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getDifficulty().equalsIgnoreCase(difficulty)) {
                result.add(post);
            }
        }
        return result;
        */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Post> searchByGenre(Genre genre) {
        /*
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : posts) {
            if (post.getGenre().equalsIgnoreCase(genre)) {
                result.add(post);
            }
        }
        return result;
        */
        return null; // Placeholder for actual implementation
    }
}
