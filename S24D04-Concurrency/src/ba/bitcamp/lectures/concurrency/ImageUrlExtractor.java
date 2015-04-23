package ba.bitcamp.lectures.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Dummy task of extracting image links from web page.
 * This is attempt of having task that involves IO, but since it is same file, it is not noticable.
 * 
 * @author emir
 *
 */
public class ImageUrlExtractor implements Runnable {
	private static final String KLIX = loadKlixPage();
	public static String loadKlixPage() {
		try (InputStream is = ImageUrlExtractor.class.getResourceAsStream("klix.html")) {
			return new java.util.Scanner(is).useDelimiter("\\A").next();
		} catch (IOException e) {
			// should not happen
			throw new RuntimeException(e);
		}
	}

	public static ImageUrlExtractor getPreloadedKlixExtrator() {
		return new ImageUrlExtractor(KLIX);
	}
	
	public static ImageUrlExtractor getKlixExtrator() {
		return new ImageUrlExtractor(null);
	}

	private static final Pattern IMG_URL = Pattern.compile("<img\\s+.*?\\ssrc=['\"]?(.+?)[ '\"]", Pattern.CASE_INSENSITIVE);
	private String html;
	private volatile int foundLinks;
	
	
	public ImageUrlExtractor(String html) {
		this.html = html;
	}

	@Override
	public void run() {
		if (html == null) {
			parse(loadKlixPage());
		} else {
			parse(html);
		}
	}
	
	private void parse(String html) {
		Matcher m = IMG_URL.matcher(html);
		int cnt = 0;
		while (m.find()) {
			cnt++;
		}
		foundLinks = cnt;
	}
	
	public int foundLinks() {
		return foundLinks;
	}
}
