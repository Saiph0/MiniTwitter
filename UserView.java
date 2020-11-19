// User View Class - Generates UI to view user as admin

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class UserView extends javax.swing.JFrame implements Observer {

   private String username;
   private User user;
   private ArrayList<User> users;
   private HashMap<String, UserView> userViews;
   private ArrayList<String> userIDs;
   DefaultListModel<String> followingModel;
   DefaultListModel<String> newsfeedModel;
   
   private javax.swing.JButton followButton;
   private javax.swing.JList<String> followingList;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JScrollPane jScrollPane4;
   private javax.swing.JList<String> newsFeedList;
   private javax.swing.JButton tweetButton;
   private javax.swing.JTextArea tweetTextArea;
   private javax.swing.JTextArea userViewIDTextArea;

   // Constructor
   public UserView(User user, ArrayList<String> userIDs, ArrayList<User> users, HashMap<String, UserView> userViews) {
      this.user = user;
      user.addObserver(this);
      this.username = user.getID();
      this.userIDs = userIDs;
      this.users = users;
      this.userViews = userViews;
      this.setTitle(username + "'s User View");
      followingModel = new DefaultListModel<String>();
      newsfeedModel = new DefaultListModel<String>();
      generateComponents();
   }

   // Generates UI for User View
    private void generateComponents() {
        jScrollPane1 = new javax.swing.JScrollPane(userViewIDTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userViewIDTextArea = new javax.swing.JTextArea();
        followButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        followingList = new JList(user.getFollowing().toArray());
        jScrollPane3 = new javax.swing.JScrollPane();
        tweetTextArea = new javax.swing.JTextArea();
        tweetButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        newsFeedList = new JList(user.getNewsFeed().toArray());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userViewIDTextArea.setColumns(20);
        userViewIDTextArea.setLineWrap(true);
        userViewIDTextArea.setRows(5);
        jScrollPane1.setViewportView(userViewIDTextArea);

        followButton.setText("Follow User");
        followButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followButtonActionPerformed(evt);
            }
        });

        followingModel.addElement("Current Following: ");
        jScrollPane2.setViewportView(followingList);

        tweetTextArea.setColumns(20);
        tweetTextArea.setLineWrap(true);
        tweetTextArea.setRows(5);
        tweetTextArea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(tweetTextArea);

        tweetButton.setText("Post Tweet");
        tweetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tweetButtonActionPerformed(evt);
            }
        });

        newsfeedModel.addElement("News Feed");
        jScrollPane4.setViewportView(newsFeedList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tweetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(followButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(followButton, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tweetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    // Processes input of Follow User button
    private void followButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (!userIDs.contains(userViewIDTextArea.getText())) {
            JOptionPane.showMessageDialog(null, "This user does not exist.", "Error", JOptionPane.INFORMATION_MESSAGE);
            userViewIDTextArea.setText("");
            return;
        } else if (user.getFollowing().contains(userViewIDTextArea.getText())) {
            JOptionPane.showMessageDialog(null, "You are already following this user.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if (user.getID().equals(userViewIDTextArea.getText())) {
            JOptionPane.showMessageDialog(null, "You cannot follow yourself!", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            followingModel.addElement("- " + userViewIDTextArea.getText());
            followingList.setModel(followingModel);
            user.follow(userViewIDTextArea.getText());
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getID().equals(userViewIDTextArea.getText())) {
                    users.get(i).attach(user);
                }
            }
        }
        userViewIDTextArea.setText("");
        revalidate();
        repaint();
    }

    // Processes input of Post Tweet button
    private void tweetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (tweetTextArea.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tweets cannot be blank!", "Tweet Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            user.tweet(tweetTextArea.getText());
            newsfeedModel.insertElementAt(user.getNewsFeed().get(0), 1);
            newsFeedList.setModel(newsfeedModel);
            tweetTextArea.setText("");

            // Observer Pattern - updates news feeds after tweet
            List<User> followers = user.getObserver();
            int isize = followers.size();
            String msg = user.getNewsFeed().get(0);
            for (int j = 0; j < isize; j++) {
                User myuser = followers.get(j);
                UserView myUserView = userViews.get(myuser.getID());
                myuser.updateNewsFeed(msg);
                update(myuser, msg);

                myUserView.newsfeedModel.insertElementAt(msg, 1);
                myUserView.newsFeedList.setModel(newsfeedModel);
                myUserView.tweetTextArea.setText("");
                myUserView.revalidate();
                myUserView.repaint();

            }
            revalidate();
            repaint();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List) {
        }
    }

}
