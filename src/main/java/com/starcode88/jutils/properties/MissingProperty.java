package com.starcode88.jutils.properties;

/**
 * Exception which will be thrown when a key is 
 * expected to exist in a property file but it was not defined.
 * @author Marco Wehnert
 *
 */
public class MissingProperty extends Exception {

	private static final long serialVersionUID = -4469195219811637855L;

	@SuppressWarnings("unused")
	private MissingProperty() {}

	/**
	 * Constructor with the key
	 * @param key The key which was expected to exist in the property file.
	 */
	public MissingProperty(String key) {
		super("The  property \"" +key+"\" is missing");
	}

}
