package abadboy.com.crawler.image;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BookImageCrawlerController {
	private static final Logger logger = LoggerFactory.getLogger(ImageCrawlController.class);
	private static String rootFolder = "/data";
	private static int numberOfCrawlers = 5;
	private static String storageFolder = "/data/image";


	public static void main(String[] args) throws Exception {

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(rootFolder);

    /*
     * Since images are binary content, we need to set this parameter to
     * true to make sure they are included in the crawl.
     */
		config.setIncludeBinaryContentInCrawling(true);

		String[] crawlDomains = {"https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start="};



		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		/*for (String domain : crawlDomains) {
			controller.addSeed(domain);
		}*/

		for (int i = 0; i < 100; i=i+20) {
			controller.addSeed(crawlDomains[0]+i+"&type=S");
		}
		ImageCrawler.configure(crawlDomains, storageFolder);

		controller.start(ImageCrawler.class, numberOfCrawlers);
	}
}
