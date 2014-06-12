package starcatalog

import org.springframework.batch.admin.service.JobService
import org.springframework.batch.core.launch.JobOperator
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by vsrinivasan on 6/12/2014.
 */
@Controller
@RequestMapping("/batch")
class BatchAdminController {
	JobOperator jobOperator
	JobService jobService

	@RequestMapping("jobstatus")
	def jobs() {
		def model = [jobCount: jobService.countJobs(),
		 jobList: jobService.listJobs(0, 10),
		 jobExecutionsCount: jobService.countJobExecutions(),
		 jobInstancesCount: jobService.countJobInstances(),
		 listJobExecutions: jobService.listJobExecutions(0, 10),
		 stepNames: jobService.getStepNamesForJob('starCatalogExtractJob')]

		return new ModelAndView("views/jobstatus", model)
	}
}

