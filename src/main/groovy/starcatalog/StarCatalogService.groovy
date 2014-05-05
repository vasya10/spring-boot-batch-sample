package starcatalog

import org.springframework.stereotype.Service

@Service
class StarCatalogService {

	public static final int PENDING_STATUS = 4

	Star[] findAllStars() {
		println "findAllStars"
		return Star.findAll()
	}
}
