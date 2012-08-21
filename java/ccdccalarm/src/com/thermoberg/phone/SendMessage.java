package com.thermoberg.phone;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;key&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;phoneNumber&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;message&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "key", "phoneNumber", "message" })
@XmlRootElement(name = "SendMessage")
public class SendMessage {

	@XmlElementRef(name = "key", namespace = "http://www.thermoberg.com/Services/TelephoneService", type = JAXBElement.class)
	protected JAXBElement<String> key;
	@XmlElementRef(name = "phoneNumber", namespace = "http://www.thermoberg.com/Services/TelephoneService", type = JAXBElement.class)
	protected JAXBElement<String> phoneNumber;
	@XmlElementRef(name = "message", namespace = "http://www.thermoberg.com/Services/TelephoneService", type = JAXBElement.class)
	protected JAXBElement<String> message;

	/**
	 * Gets the value of the key property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public JAXBElement<String> getKey() {
		return key;
	}

	/**
	 * Sets the value of the key property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public void setKey(JAXBElement<String> value) {
		this.key = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the phoneNumber property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public JAXBElement<String> getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public void setPhoneNumber(JAXBElement<String> value) {
		this.phoneNumber = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the message property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public JAXBElement<String> getMessage() {
		return message;
	}

	/**
	 * Sets the value of the message property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	public void setMessage(JAXBElement<String> value) {
		this.message = ((JAXBElement<String>) value);
	}

}
