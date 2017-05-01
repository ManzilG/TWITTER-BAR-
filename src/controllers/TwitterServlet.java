package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import models.Item;
import service.TweetClass;
import service.TweetCount;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.TwitterException;
import twitter4j.conf.*;

/**
 * Servlet implementation class TwitterServlet
 */
@WebServlet("/TwitterServlet")
public class TwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String genre = request.getParameter("genre");
		//String trends=request.getParameter("trends");
		//System.out.println("1: " + genre);
		if (genre == null) {
			genre = (String)session.getAttribute("genre");
			//trends=(String) session.getAttribute("trends");
		}
		//System.out.println("2: " + (String)session.getAttribute("genre"));
		if (genre == null) {
			genre = "romance";
		}
//		System.out.println("3: " + genre);
		session.setAttribute("genre", genre);
		genre = genre.toLowerCase();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		TweetClass value = new TweetClass();
		try {
			out.write(value.acceptTwitt(genre));
			/*value.acceptTwitt(genre).forEach((temp)->{
				out.println(temp);
			});*/
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String latitude=request.getParameter("latitude");
		String longitude=request.getParameter("longitude");
		System.out.println("%%%%%%$$$$$$$"+latitude+"$%%%%%%%%%%");
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		TweetCount value=new TweetCount();
			out.write(value.getTweets(latitude,longitude));
			/*value.acceptTwitt(genre).forEach((temp)->{
				out.println(temp);
			});*/
		
	}
	

}
