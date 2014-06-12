package starcatalog

import org.springframework.batch.admin.service.JobService
import org.springframework.batch.core.launch.JobOperator
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vsrinivasan on 3/30/2014.
 */
@RestController
@RequestMapping("/starcatalog")
class StarCatalogController {

	ConfigObject configObject
	StarCatalogService starCatalogService

	@RequestMapping("init")
	def init() {
		if (Star.count() == 0) {
			starCatalogService.initializeRecords()
		}

		return "StarCatalog Records: " + Star.count()
	}

	@RequestMapping("list")
	@ResponseBody
	List<Star> list() {
		List<Star> starList = starCatalogService.findAllStars()

		//ResponseBody cannot handle Jackson conversion of Domain objects out of the box
		//other solutions: write custom mapper or disable Jackson's Serializer.FAIL_EMPTY_BEANS
		List<Star> stars = []
		starList.each { Star s ->
			stars << [id: s.id, name: s.name, description: s.description, distanceInLightYears: s.distanceInLightYears, imageLink: s.imageLink]
		}
		return stars
	}

	@RequestMapping(value="create", method=RequestMethod.POST)
	String create(@RequestBody final _star) {
		Star star = new Star()
		star.name = _star.name
		star.description = _star.description
		star.distanceInLightYears = _star.distanceInLightYears
		star.imageLink = _star.imageLink

		star.save()

		return "Successfully added ${star.name} to the Catalog!"
	}
}
