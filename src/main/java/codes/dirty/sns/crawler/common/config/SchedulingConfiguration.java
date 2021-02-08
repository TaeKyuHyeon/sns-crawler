package codes.dirty.sns.crawler.common.config;

import codes.dirty.sns.crawler.module.spotv.model.SpotvVideo;
import codes.dirty.sns.crawler.module.spotv.service.SpotvSchedulingService;
import codes.dirty.sns.crawler.module.jirye.model.JiryeRoom;
import codes.dirty.sns.crawler.module.jirye.service.JiryeSchedulingService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
public class SchedulingConfiguration {

    private final JiryeSchedulingService jiryeSchedulingService;
    private final SpotvSchedulingService spotvSchedulingService;

    public SchedulingConfiguration(JiryeSchedulingService jiryeSchedulingService,
                                   SpotvSchedulingService spotvSchedulingService) {
        this.jiryeSchedulingService = jiryeSchedulingService;
        this.spotvSchedulingService = spotvSchedulingService;
    }

    @Scheduled(initialDelay = 100, fixedRate = 3600000)  // TODO: Extract param as properties
    public void executeJirye() {
        final List<JiryeRoom> crawledResult = jiryeSchedulingService.crawl();
        jiryeSchedulingService.handleCrawledResult(crawledResult);
    }

    @Scheduled(initialDelay = 1000, fixedRateString = "${spotv.interval}")  // TODO: Extract param as properties
    public void executeSpotv() {
        final List<SpotvVideo> crawledResult = spotvSchedulingService.crawl();
        spotvSchedulingService.handleCrawledResult(crawledResult);
    }
}
