package org.interview.twitter.service;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;
import org.springframework.social.twitter.api.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.collection.IsMapContaining;
import java.util.Date;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TweetListenerTest {

  @Test
  public void defaultValues() {
    TweetListener listener = new TweetListener();

    assertEquals("Default tweet count should be zero.", 
      Integer.valueOf(0), Integer.valueOf(listener.getTweetCount()));

    assertNotNull(listener.retrieveTweets());
  }

  @Test
  public void onTweetListener() {
    TweetListener listener = new TweetListener();
    Tweet tweet = new Tweet(1L, "111", "test tweet", new Date(), "test user", "test profile pic",
      1L, 1, "test lang", "test source");

    assertThat(listener.retrieveTweets().size(), is(0));

    listener.onTweet(tweet);

    assertThat(listener.getTweetCount(), is(1));

    assertThat(listener.retrieveTweets().size(), is(1));

    assertThat(listener.retrieveTweets(), IsMapContaining.hasKey("test user"));

  }
}