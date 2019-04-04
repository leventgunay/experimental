import React, { useState, useEffect } from "react";
import { TweetBox } from './TweetBox';
import {
  Button,
  Card,
  CardText,
  CircularProgress,
  Toolbar
} from 'react-md';

export const App = () => {

  const [tweets, setTweets] = useState({})

  const [loading, setLoading] = useState(true)

  const startStreaming = () => {
    setLoading(true)
    fetch("/twitter/streaming", {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }).then(res => res.json())
    .then((res) => {
      setTweets(res)
      setLoading(false)
    });
  }

  useEffect(startStreaming, [])

  let listing = <CircularProgress id="loading" />

  const tweetsGroupedByUser = []

  if (!loading) {
    for(const user in tweets) {
      tweetsGroupedByUser.push(
        <TweetBox tweets={tweets[user]} key={user} />
      )
    }
    listing = tweetsGroupedByUser
    if (listing.length < 1) {
      listing = 'No tweets found..'
    }
  }
  
  return (
    <Card className="md-block-centered">
      <Toolbar colored title="Tweets" actions={
        <Button key="action" icon disabled={loading} 
          onClick={startStreaming}>autorenew</Button>
      } />

      <CardText> { listing } </CardText>
    </Card>
  );
}
