package org.interview.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.interview.twitter.service.TwitterStreamingService;
import org.springframework.social.twitter.api.Tweet;
import java.util.Map;
import java.util.List;


@RestController
public class TwitterStreamingController {

	@Autowired
	private TwitterStreamingService streaming;

	@ResponseBody
	@GetMapping(path = "/twitter/streaming", consumes = "application/json", produces = "application/json")
	public Map<String, List<Tweet>> streamingTweets(@RequestParam(name="query", required=false, defaultValue="bieber") String query) {
		return this.streaming.startStreaming(query);
	}

}