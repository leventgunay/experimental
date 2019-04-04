package org.interview.twitter.service;

import org.springframework.social.twitter.api.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class TweetListener implements StreamListener {
  
  private Integer tweetCounter = 0;

  private Map<String,List<Tweet>> tweets = new HashMap<String, List<Tweet>>();

  public Integer getTweetCount() {
    return this.tweetCounter;
  }

  public Map<String,List<Tweet>> retrieveTweets() {
    return tweets;
  }

  @Override
  public void onTweet(Tweet tweet) {
      tweetCounter++;
      String user = tweet.getFromUser();
      List<Tweet> list = this.tweets.get(user);
      if (list == null) {
        list = new ArrayList<Tweet>();
      }
      list.add(tweet);
      this.tweets.put(user, list);
  }

  @Override
  public void onDelete(StreamDeleteEvent streamDeleteEvent) {
      System.out.println("Tweet deleted!");
  }

  @Override
  public void onLimit(int i) {
      System.out.println("Track limited!");
  }

  @Override
  public void onWarning(StreamWarningEvent streamWarningEvent) {
      System.out.println("Warning! Client is stalling, the connection may be closed!");
  }


}
