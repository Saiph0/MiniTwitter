// User Class - Holds User's ID and Status

import java.util.*;

// Visitor Design Pattern
public class User extends Observable implements TreeInterface {

    private String uniqueID;
    private List<String> following;
    private List<String> messages;
    private List<String> newsFeed;
    private List<User> followers;
    private int positiveCount = 0;
    private int messageCount = 0;
    private String[] positiveWords = {"good", "great", "excellent"};

    public User(String uniqueID) {
        this(uniqueID, false);
        followers = new ArrayList<>();
        following = new ArrayList<>();
        messages = new ArrayList<>();
        newsFeed = new ArrayList<>();
    }

    public User(String uniqueID, boolean property) {
        this.uniqueID = uniqueID;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        messages = new ArrayList<>();
        newsFeed = new ArrayList<>();
    }

    public void follow(String user) {
        following.add(user);
    }

    public void tweet(String message) {
        messages.add(message);
        setChanged();
        notifyObservers(message);
        newsFeed.add(0, "- " + uniqueID + ": " + message);
        setChanged();
        notifyObservers(newsFeed);
        for (String word : positiveWords) {
            if (message.toLowerCase().contains(word)) {
                positiveCount++;
            }
        }
        messageCount++;
    }

    public int getMessageCount() {
        return messageCount;
    }
    
    public List<User> getObserver() {
        return followers;
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getFollowing() {
        return following;
    }

    public int getPositiveCount() {
        return positiveCount;
    }

    public void attach(User user) {
        followers.add(user);
    }

    public List<String> getNewsFeed() {
        return newsFeed;
    }

    public String getID() {
        return uniqueID;
    }

    public String toString() {
        return uniqueID;
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            newsFeed.add("[" + ((User) o).getID() + "] - " + (String) arg);
        }
    }
    
    public void  updateNewsFeed(String msg) {
      newsFeed.add(msg);
   }
}
