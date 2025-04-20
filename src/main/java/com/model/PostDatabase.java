package com.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

/**
 * PostDatabase class that holds all the posts and has methods to search and sort them
 * This class is a singleton class that is used to access the posts from the DataLoader class
 * Includes methods to search and sort the posts by different criteria
 * Includes a method to add a post to the database and a method to save the posts to the data file
 * @author Ian Attmore
 * @author Owen CoulamOwen Coulam
 */
public class PostDatabase {
    private static PostDatabase postDatabase;
    private ArrayList<Post> postList;
    private Map<UUID, Post> postMap;


    private PostDatabase() {
        postList = DataLoader.getPosts();
        postMap = new HashMap<UUID, Post>();
        for(Post post : postList) {
            postMap.put(post.getUUID(), post);
        }
    }

    /**
     * Singleton method to get the instance of the PostDatabase
     * If the instance does not exist, it creates a new instance
     * @return the instance of the PostDatabase
     */
    public static PostDatabase getInstance() {
        if (postDatabase == null) {
            postDatabase = new PostDatabase();
        }
        return postDatabase;
    }

    public ArrayList<Post> getPostList() {
        return postList;
    }

    public Map<UUID, Post> getPostMap() {
        return postMap;
    }

    /**
     * Method to search the posts by the user who created them
     * @param user the username of the user to search for
     * @return an ArrayList of posts that were created by the user
     */
    public ArrayList<Post> searchByUser(String user) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : postList) {
            if (post.getAuthor().getUsername().equalsIgnoreCase(user)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     * Method to search the posts by the title of the post
     * @param title the title of the post to search for
     * @return an ArrayList of posts that have the same title
     */
    public ArrayList<Post> searchByName(String title) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : postList) {
            if (post.getTitle().equals(title)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     * Method to search the posts by the song that the post is about
     * @param song the name of the song to search for
     * @return an ArrayList of posts that are tagged with the song
     */
    public ArrayList<Post> searchBySong(String song) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : postList) {
            if (post.getSong().getName().equalsIgnoreCase(song)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     * Method to search the posts by the artist of the song that the post is about
     * @param artist the name of the artist to search for
     * @return an ArrayList of posts that are tagged with the artist
     */
    public ArrayList<Post> searchByArtist(String artist) {
        ArrayList<Post> result = new ArrayList<Post>();
        for (Post post : postList) {
            if (post.getSong().getArtist().equalsIgnoreCase(artist)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     * Methods to sort the posts by most recent and most liked
     * @return an ArrayList of posts that are sorted by the criteria
     */

    public ArrayList<Post> sortByMostRecent() {
        postList.sort( (a,b) -> b.getDate().compareTo(a.getDate()) );
        return postList;
    }

    public ArrayList<Post> sortByMostLiked() {
        postList.sort( (a,b) -> Integer.compare(a.getFavorites(), b.getFavorites()) );
        return postList;
    }

    /**
     * Method to add a post to the database
     * @param post the post to be added
     * @return true if the post is added to the database
     */
    public boolean addPost(Post post) {
        if(postList.add(post)) {
            postMap.put(post.getUUID(), post);
            return true;
        } else {
            return false;
        }
    }

    public void save() {
        DataWriter.savePosts();
    }

    public void populate() {
        for(Post post : postList) {
            post.setSong(SongDatabase.getInstance().getSongMap().get(post.getSongUUID()));
            post.setAuthor(UserDatabase.getInstance().getUserMap().get(post.getAuthorUUID()));
        }
    }
}
