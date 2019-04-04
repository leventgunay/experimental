package org.interview.twitter.service;

import org.springframework.stereotype.Service;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Map;
import java.util.List;

@Service
public class TwitterStreamingService {

  private static int MAX_TWEET_COUNT = 100;
  private static long MAX_STREAMING_TIME = 30000;

  @Autowired
	private TwitterTemplate twitter;

  public Map<String, List<Tweet>> startStreaming(String text) {

    FilterStreamParameters filterParams = new FilterStreamParameters();
    filterParams.track( text );

    TweetListener tweets = new TweetListener();

    StreamingOperations streamingOperations = twitter.streamingOperations();
    Stream stream = streamingOperations.filter( filterParams, Arrays.asList( tweets ) );

    long startTime = System.currentTimeMillis();
    while (tweets.getTweetCount() < MAX_TWEET_COUNT && 
            System.currentTimeMillis() - startTime < MAX_STREAMING_TIME ) {
              // keep streaming..
    }

    stream.close();

    return tweets.retrieveTweets();
  }

}
