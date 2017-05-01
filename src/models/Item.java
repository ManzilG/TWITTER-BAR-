package models;

public class Item {
	private String genre;
	private String imageURL;
	private String text;
	private String link;

	public Item(String genre, String imageURL, String text, String link) {
		this.genre = genre;
		this.imageURL = imageURL;
		this.text = text;
		this.link = link;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Item [imageURL=" + imageURL + ", text=" + text + ", link=" + link + "]";
	}

}
