package starcatalog

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by vsrinivasan on 4/25/2014.
 */
@Service
class Bootstrap implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class.getName())

	@Override
	@Transactional
	void afterPropertiesSet() throws Exception {
		logger.info "creating boostrap records"
		new Star(name:'aldebran', description: 'Aldebran', distanceInLightYears: 50).save(flush:true)
		new Star(name:'arctrus',  description: 'Arctrus', distanceInLightYears: 100).save(flush:true)
		new Star(name:'betelguese', description: 'Betelgeuse', distanceInLightYears: 150).save(flush:true)
	}
}
