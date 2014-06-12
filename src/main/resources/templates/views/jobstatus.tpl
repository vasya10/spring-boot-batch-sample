yieldUnescaped '<!DOCTYPE html>'
html {
	head {
		title('StarCatalog Batch Status')
		link(rel: 'stylesheet', href: '/app/styles/bootstrap.css')
	}
	body {
		div(class: 'container') {
			h2('StarCatalog Batch Status')
			div(class: 'navbar') {
				div(class: 'navbar-inner') {
					a(class: 'brand', href: '/app/index.html', 'Home')
				}
				div(class: 'navbar-inner') {
					a(class: 'brand', href: 'http://projects.spring.io/spring-boot/', 'Spring Boot')
				}
			}

			hr()
			h3('Job Summary')
			ul(class: 'list-unstyled') {
				li('Total Jobs executed: ' + jobCount)
				li('No. of Job Executions:' + jobExecutionsCount)
			}

			h3('Job History')
			small('Last 10 jobs')
			ul(class: 'list-unstyled') {
				listJobExecutions.each { jobExecution ->
					li(jobExecution.jobInstance.jobName + ' - ' + jobExecution.startTime + ' - ' + jobExecution.endTime + ' - Exit Status(' + jobExecution.exitStatus.exitCode + ')')
				}
			}

			hr()
		}
	}
}