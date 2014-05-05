package starcatalog

import org.springframework.batch.admin.service.JobService
import org.springframework.batch.core.launch.JobOperator
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vsrinivasan on 3/30/2014.
 */
@RestController
class StarCatalogController {

	ConfigObject configObject
	StarCatalogService starCatalogService
	JobOperator jobOperator
	JobService jobService

	@RequestMapping("/starCatalog/jobs")
	String jobs() {
		StringBuilder stringBuilder = new StringBuilder()
		stringBuilder.append('JobCount: ').append(jobService.countJobs()).append('<br>')
		stringBuilder.append('JobList: ').append(jobService.listJobs(0,5)).append('<br>')
		stringBuilder.append('JobExecutions.count: ').append(jobService.countJobExecutions()).append('<br>')
		stringBuilder.append('JobInstances.count: ').append(jobService.countJobInstances('starCatalogExtractJob')).append('<br>')
		stringBuilder.append('StepNamesForJob: ').append(jobService.getStepNamesForJob('starCatalogExtractJob')).append('<br>')
		return stringBuilder.toString()
	}

	@RequestMapping("/starCatalog/list")
	String list() {
		StringBuilder stringBuilder = new StringBuilder()
		Star[] stars = starCatalogService.findAllStars()
		stars?.each { Star star ->
			stringBuilder.append(star.id).append(" - ").append(star.description).append(" - ").append(star.distanceInLightYears).append('<br>')
		}
		return stringBuilder.toString()
	}
}
