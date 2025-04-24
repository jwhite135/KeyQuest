package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The UserDatabase class is a singleton class that holds all the users in the system
 * It is used to get a user by their email and password and to add a user to the system
 * It is also used to get all the users in the system
 * @author Ian Attmore
 */
public class UserDatabase {
    private static UserDatabase userDatabase;
    private static ArrayList<User> userList;
    private static Map<UUID, User> userMap;

    /**
     * Constructor for the UserDatabase class
     * Initializes the users ArrayList by calling the DataLoader class
     */
    private UserDatabase() {
        userList = DataLoader.getUsers();
        userMap = new HashMap<UUID, User>();
        for(User user : userList) {
            userMap.put(user.getUUID(), user);
        }
    }

    /**
     * Singleton method to get the instance of the UserDatabase
     * If the instance does not exist, it creates a new instance
     * @return the instance of the UserDatabase
     */
    public static UserDatabase getInstance() {
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }
        return userDatabase;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public Map<UUID, User> getUserMap() {
        return userMap;
    }
    /**
     * Method to get a user by their email and password
     * @return the user if the user exists, null if the user does not exist
     */
    public User getUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Method to add a user to the UserDatabase
     * @return false if the user already exists, true if the user is added to the UserDatabase
     */
    public static boolean addUser(User user) {
        if (user == null) {
            return false;
        } 
        for (User existingUser : userList) {
            if (user.getUsername().equals(existingUser.getUsername())) {
                return false;
            }
        }
        userList.add(user);
        userMap.put(user.getUUID(), user);
        return true;
    }

    public void save() {
        DataWriter.saveUsers();
    }

    public void populate() {
        for(User user : userList) {
            ArrayList<UUID> favSongIDs = user.getFavoriteSongsUUIDs();
            ArrayList<Song> favSongs = new ArrayList<Song>();
            SongDatabase songDatabase = SongDatabase.getInstance();
            for(UUID id : favSongIDs) {
                if (songDatabase.getSongMap().containsKey(id)) {
                    favSongs.add(songDatabase.getSongMap().get(id));
                }
            }
            user.setFavoriteSongs(favSongs);
            ArrayList<UUID> favPostIDs = user.getFavoritePostsUUIDs();
            ArrayList<Post> favPosts = new ArrayList<Post>();
            PostDatabase postDatabase = PostDatabase.getInstance();
            for(UUID id : favPostIDs) {
                if (postDatabase.getPostMap().containsKey(id)) {
                    favPosts.add(postDatabase.getPostMap().get(id));
                }
            }
            user.setFavoritePosts(favPosts);
            ArrayList<UUID> friendIDs = user.getFriendsUUIDs();
            ArrayList<User> friends = new ArrayList<User>();
            for(UUID id : friendIDs) {
                if (userMap.containsKey(id)) {
                    friends.add(userMap.get(id));
                }
            }
            user.setFriends(friends);
            ArrayList<UUID> postIDs = user.getAuthoredPostsUUIDs();
            ArrayList<Post> posts = new ArrayList<Post>();
            for(UUID id : postIDs) {
                if (postDatabase.getPostMap().containsKey(id)) {
                    posts.add(postDatabase.getPostMap().get(id));
                }
            }
            user.setAuthoredPosts(posts);
        }
    }
}
