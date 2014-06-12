package starcatalog

import groovy.util.logging.Log
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by vsrinivasan on 4/25/2014.
 */
@Service
@Log
class Bootstrap implements InitializingBean {

	@Override
	void afterPropertiesSet() throws Exception {
//		log.info "creating boostrap records"
//		new Star(name:'aldebran', description: 'Aldebran', distanceInLightYears: 50, imageLink: 'http://astropixels.com/stars/images/Aldebaran-01w.jpg').save(flush:true)
//		new Star(name:'M1',  description: 'Crab Nebula', distanceInLightYears: 100, imageLink:'http://astropixels.com/supernovae/thumb/M1-01m.jpg').save(flush:true)
//		new Star(name:'sb2014J', description: 'SN 2014J', distanceInLightYears: 150, imageLink: 'http://astropixels.com/supernovae/thumb/SN2014J-B02-2m.jpg').save(flush:true)
//		new Star(name:'M27', description: 'Dumb Bell Nebula', distanceInLightYears: 25, imageLink: 'http://astropixels.com/planetarynebulae/thumb/M27-01n.jpg').save(flush:true)
//		new Star(name:'M57', description: 'Ring Nebula', distanceInLightYears: 25, imageLink: 'http://astropixels.com/planetarynebulae/thumb/M57-01n.jpg').save(flush:true)
//		new Star(name:'M97', description: 'Owl Nebula', distanceInLightYears: 25, imageLink: 'http://astropixels.com/planetarynebulae/thumb/M97-01n.jpg').save(flush:true)
	}
}
