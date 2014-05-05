package starcatalog

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.NonTransientResourceException
import org.springframework.batch.item.ParseException
import org.springframework.batch.item.UnexpectedInputException

/**
 * Created by vsrinivasan on 5/2/2014.
 */
class StarItemReader implements ItemReader<Star> {

	private static final Logger logger = LoggerFactory.getLogger(StarItemReader.class.getName())

	Iterator iterator
	StarCatalogService starCatalogService

	StarItemReader() {
		super()
	}

	public void init() {
		logger.info "starItemReader.init"
		this.iterator = starCatalogService.findAllStars().iterator()
	}

	@Override
	Star read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		iterator.hasNext() ? iterator.next() : null
	}
}
