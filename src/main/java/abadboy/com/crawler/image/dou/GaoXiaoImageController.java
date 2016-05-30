package abadboy.com.crawler.image.dou;

import abadboy.com.crawler.image.ImageCrawlController;
import abadboy.com.crawler.image.ImageCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter zhao on 5/30/2016.
 */
public class GaoXiaoImageController  {
	private static final Logger logger = LoggerFactory.getLogger(ImageCrawlController.class);
	private static String rootFolder = "/data";
	private static int numberOfCrawlers = 5;
	private static String storageFolder = "/data/gaoxiao";

	public static void main(String[] args) throws Exception {

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(rootFolder);
		config.setIncludeBinaryContentInCrawling(true);

		String[] url = {"https://www.douban.com/group/320130/member_search?search_text=%E4%B8%8A%E6%B5%B7&start="};

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		for (int i = 0; i < 1000; i=i+35) {
			controller.addSeed(url[0]+i);
		}


		ImageCrawler.configure(url, storageFolder);
		controller.start(ImageCrawler.class,numberOfCrawlers);
	}



}
