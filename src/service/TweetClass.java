package service;
import models.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.*;

public class TweetClass {

	public String acceptTwitt(String search) throws TwitterException {

		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true).setOAuthConsumerKey("Q2hFg2FJqYhetMlSzJAhX7qi9")
				.setOAuthConsumerSecret("rhG6dzdlmINmfZ2cUFJt6VJJNcR5O8FmfeddTMq106K5M9ezUv")
				.setOAuthAccessToken("2714479254-aV14OvWYkZTtwLOuHHiGn2IqFQYxEgyyGK6tF2O")
				.setOAuthAccessTokenSecret("wc8w04WpG7LMZxiwAQfIuC4m6whcPCRpZYPU9jLyi7P51");
		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();
		if (!search.equals("trends")) {
			Query query = new Query(search);
			query.setCount(1000);
			QueryResult result;
			int count = 0;
			result = twitter.search(query);
			List<Status> tweets = result.getTweets();
			List<Item> items = new ArrayList<Item>();
			String imageURL, text, link;
			for (Status tweet : tweets) {
				if (tweet.getMediaEntities().length > 0) {

					MediaEntity mt = tweet.getMediaEntities()[0];
					imageURL = mt.getMediaURL();
					// System.out.println("%%%%%%%%%%%%%%%%%%%"+tweet.getText()+"%%%%%%%%%%%%%%%%%%%");
					text = tweet.getText().substring(0, tweet.getText().indexOf("https"));
					link = mt.getURL();
					Item i = new Item(search, imageURL, text.toUpperCase(), link);
					items.add(i);
					count++;
				}

				if (count >= 15)
					break;
			}
			return new Gson().toJson(items);
		}
		else{
			List<Status> trends=twitter.getHomeTimeline();
			List<Item> items = new ArrayList<Item>();
			String imageURL, text, link;
			for (Status trend : trends) {
				if (trend.getMediaEntities().length > 0) {
					System.out.println("Printing");
					MediaEntity mt = trend.getMediaEntities()[0];
					imageURL = mt.getMediaURL();
					text = trend.getText().substring(0, trend.getText().indexOf("https"));
					System.out.println(text);
					link = mt.getURL();
					Item i = new Item(search, imageURL, text.toUpperCase(), link);
					items.add(i);
				}

			}
			return new Gson().toJson(items);
			
		}
	}
}
