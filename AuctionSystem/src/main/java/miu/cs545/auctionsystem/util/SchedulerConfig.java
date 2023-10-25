package miu.cs545.auctionsystem.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@Configuration
@EnableAsync
public class SchedulerConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolBidClosingScheduler");
        return threadPoolTaskScheduler;
    }
}
