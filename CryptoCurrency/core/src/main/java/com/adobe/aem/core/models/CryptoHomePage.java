/** 
 * The CryptoHomePage class implements an application that
 * simply displays the data entered into the dialog
 * of the component.
 */

package com.adobe.aem.core.models;

import org.apache.sling.api.resource.Resource;

/**
 * This is the model class to fetch all the dialog values for the Cryptotile Component.
 * @author Vikram
 *
 */
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class CryptoHomePage {

	@Inject @Default(values="BTC")
	public String label;

	@Inject @Default(values="/content/dam/CryptoCurrency/BTCIcon.png")
	public String logo;

	@Inject @Default(values="Bitcoin is a cryptocurrency, a form of electronic cash. It is a decentralized digital currency without a central bank or single administrator that can be sent from user-to-user on the peer-to-peer bitcoin network without the need for intermediaries")
	public String alt;

	@Inject @Default(values="/content/CryptoCurrency/en/CryptoHome/BTCPage")
	public String cta;

	/**
	 * Gets the Label of the currency.
	 * @return this Profit.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the Logo path.
	 * @return this logo.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Gets the Alt for the Logo.
	 * @return this Alt.
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * Gets the Cta Path for the logo.
	 * @return this Cta.
	 */
	public String getCta() {
		return cta;
	}

}
