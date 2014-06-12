package starcatalog

import groovy.transform.ToString

import grails.persistence.Entity

@Entity
@ToString
class Star {

	String name
	String description
	BigDecimal distanceInLightYears
	String imageLink

	static mapping = {
		version false
	}

	static constraints = {
	}
}
