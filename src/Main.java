import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	private static Queue<String> queue = new LinkedList<String>();
	private static HashSet<String> hs = new HashSet<String>();

	public static void crawl(String url) {
		Document doc;
		try {
			// need http protocol
			doc = Jsoup.connect(url).userAgent("Mozilla").get();
			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.toString().contains("www.")
						&& link.toString().contains(".com")
						&& (link.toString().contains("http://") || link
								.toString().contains("https://"))) {
					if (!queue.contains(link.attr("href"))
							&& !hs.contains(link.attr("href"))) {
						hs.add(link.attr("href"));

						queue.add(link.attr("href"));
					} else {
						if (queue.peek().contains("www.")
								&& queue.peek().contains(".com")
								&& queue.peek().startsWith("http")) {
							System.out.println(queue.peek());
							// System.out.println(link.html());
							String temp = queue.poll();
							crawl(temp);
						} else {
							queue.poll();
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(queue.size() + " " + hs.size());
		}
	}

	public static void main(String[] args) {
		crawl("http://www.google.com/");
	}

}