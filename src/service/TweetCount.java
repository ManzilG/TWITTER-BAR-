package service;
import models.Item;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import twitter4j.GeoLocation;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetCount {
	private static final String CONSUMER_SECRET_KEY = "rhG6dzdlmINmfZ2cUFJt6VJJNcR5O8FmfeddTMq106K5M9ezUv";
	private static final String CONSUMER_KEY = "Q2hFg2FJqYhetMlSzJAhX7qi9";
	private static final String ACESS_TOKEN_SECRET = "wc8w04WpG7LMZxiwAQfIuC4m6whcPCRpZYPU9jLyi7P51";
	private static final String ACCESS_TOKEN = "2714479254-aV14OvWYkZTtwLOuHHiGn2IqFQYxEgyyGK6tF2O";

	public static String getTweets(String latitude, String longitude) {
		final Twitter twitter = new TwitterFactory().getInstance();
		final AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACESS_TOKEN_SECRET);
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET_KEY);
		twitter.setOAuthAccessToken(accessToken);
		List<Status> tweets = new ArrayList<Status>();
		int count=0;
		try {
			Query query = new Query();
			query.setCount(10);
			query.geoCode(new GeoLocation(Double.parseDouble(latitude), Double.parseDouble(longitude)), 100.0, "mi");
			QueryResult result;
			System.out.println("Searching...");
			result = twitter.search(query);
			tweets = result.getTweets();
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		// return tweets;
		List<Item> items = new ArrayList<Item>();
		String imageURL, text, link;
		for (Status tweet : tweets) {
			if (tweet.getMediaEntities().length > 0) {
				count++;
				MediaEntity mt = tweet.getMediaEntities()[0];
				imageURL = mt.getMediaURL();
				// System.out.println("%%%%%%%%%%%%%%%%%%%"+tweet.getText()+"%%%%%%%%%%%%%%%%%%%");
				text = tweet.getText().substring(0, tweet.getText().indexOf("https"));
				link = mt.getURL();
				Item i = new Item("0", imageURL, text.toUpperCase(), link);
				items.add(i);
			}
			/*if(count>=10)
				break;
*/			// return new Gson().toJson(items);
		}
		return new Gson().toJson(items);

	}
}
