package starcatalog

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

/**
 * Created by vsrinivasan on 4/2/2014.
 */
class StarItemProcessor implements ItemProcessor<Star, Star> {

	private static final Logger logger = LoggerFactory.getLogger(StarItemProcessor.class.getName())

	@Override
	Star process(Star item) throws Exception {
		return item
	}
}
