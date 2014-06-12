package starcatalog

import groovy.util.logging.Log
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Created by vsrinivasan on 4/6/2014.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@Log
class StarCatalogApplication {

	//private static final Logger logger = LoggerFactory.getLogger(StarCatalogApplication.class.getName())

	@Autowired
	JobLauncher jobLauncher

	@Autowired
	Job starCatalogExtractJob

	@Scheduled(fixedDelayString = '${extractFixedDelay}', initialDelayString = '${extractInitialDelay}')
	public void startJob() {
		log.info "startJob()"
		JobParameters jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters()
		jobLauncher.run(starCatalogExtractJob, jobParameters)
	}

	public static void main(String[] args) {
		log.info "Starting StarCatalogApplication..."
		Object[] sources = [StarCatalogApplication.class, new ClassPathResource("app-context.groovy")]
		SpringApplication.run(sources, args);
	}
}
