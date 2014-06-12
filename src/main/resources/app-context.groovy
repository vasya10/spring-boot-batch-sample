import org.springframework.batch.admin.service.SimpleJobServiceFactoryBean
import org.springframework.batch.core.configuration.support.MapJobRegistry
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean
import org.springframework.batch.core.launch.support.SimpleJobLauncher
import org.springframework.batch.core.launch.support.SimpleJobOperator
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor
import org.springframework.batch.item.file.transform.DelimitedLineAggregator
import org.springframework.core.task.SyncTaskExecutor
import starcatalog.BatchAdminController
import starcatalog.StarCatalogController
import starcatalog.StarCatalogService
import starcatalog.StarItemProcessor
import starcatalog.StarItemReader

/**
 * Created by vsrinivasan on 4/22/2014.
 */

ConfigObject config = new ConfigSlurper().parse(Config)

beans {

	xmlns([ctx: 'http://www.springframework.org/schema/context', batch: 'http://www.springframework.org/schema/batch'])
	ctx.'component-scan'('base-package': 'starcatalog')
	ctx.'annotation-config'()
//	batch.'job-repository'()

	batchAdminController(BatchAdminController) {
		jobOperator = ref('jobOperator')
		jobService = ref('jobService')
	}

	starCatalogService(StarCatalogService)

	starCatalogController(StarCatalogController) {
		configObject = config
		starCatalogService = ref('starCatalogService')
	}

	starItemReader(StarItemReader) { bean ->
		bean.initMethod = 'init'
		starCatalogService = starCatalogService
		bean.scope = 'step'
	}

	starItemProcessor(StarItemProcessor) {
	}

	starItemWriter(FlatFileItemWriter) {
		lineAggregator = new DelimitedLineAggregator(delimiter: ',',
			fieldExtractor: new BeanWrapperFieldExtractor(names: ["name", "description", "distanceInLightYears"]))
		resource = 'file:///pocspace/github/vasya10/starcatalog-extract.txt'
	}

	batch.job(id: 'starCatalogExtractJob') {
		batch.step(id: 'step1') {
			batch.tasklet {
				batch.chunk(
					reader: 'starItemReader',
					writer: 'starItemWriter',
					processor: 'starItemProcessor',
					'commit-interval': 1
				)
			}
		}
	}

	jobRepository(JobRepositoryFactoryBean) {
		transactionManager = ref('transactionManager')
		dataSource = ref('dataSource')
	}

	jobRegistry(MapJobRegistry) { }

	jobLauncher(SimpleJobLauncher) {
		jobRepository = ref('jobRepository')
		taskExecutor = { SyncTaskExecutor executor -> }
	}

	jobExplorer(JobExplorerFactoryBean) {
		dataSource = ref('dataSource')
		//tablePrefix: "${tablePrefixValue ? tablePrefixValue + '_' : ''}".toString()
	}

	jobOperator(SimpleJobOperator) {
		jobLauncher = ref('jobLauncher')
		jobRepository = ref('jobRepository')
		jobRegistry = ref('jobRegistry')
		jobExplorer = ref("jobExplorer")
	}

	jobService(SimpleJobServiceFactoryBean) {
		jobRepository = ref('jobRepository')
		jobLauncher = ref('jobLauncher')
		jobLocator = ref('jobRegistry')
		dataSource = ref('dataSource')
		//tablePrefix: "${tablePrefixValue ? tablePrefixValue + '_' : ''}".toString()
	}

	h2WebServer(org.h2.tools.Server, '-web,-webAllowOthers,-trace,-webPort,8082') { bean ->
		bean.factoryMethod = 'createWebServer'
		bean.initMethod = 'start'
		bean.destroyMethod = 'stop'
	}

	h2Server(org.h2.tools.Server, '-tcp,-tcpAllowOthers,-trace,-tcpPort,9092') { bean ->
		bean.factoryMethod = 'createTcpServer'
		bean.initMethod = 'start'
		bean.destroyMethod = 'stop'
		bean.dependsOn = 'h2WebServer'
	}

}