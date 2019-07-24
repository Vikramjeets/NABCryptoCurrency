
package com.adobe.aem.core.models;

import static org.junit.Assert.assertEquals;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.adobe.aem.core.models.CryptoHomePage;

/**
 * This class tests CryptoHomePage
 * 
 * @author Vikram
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCryptoHomePage {

	//@Inject
	private CryptoHomePage cryptoHomePage;

	private Resource resource;
	private final String myPagePath = "/content/CryptoCurrency/en/CryptoHome";

	@Rule
	public final SlingContext context = new SlingContext(ResourceResolverType.JCR_MOCK); 

	@Before
	/**
	 * This method takes care of initial set up of data
	 * 
	 */
	public void setUp() throws Exception {            
		context.load().json( "/cryptohome.json", myPagePath);
		context.addModelsForPackage(getClass().getPackage().getName());
	}

	@Test
	/**
	 * this method test testGetLabel() method of cryptoHomePage
	 */
	public void testGetLabel() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoHomePage = resource.adaptTo(CryptoHomePage.class);  
		assertEquals(cryptoHomePage.getLabel(), "BTC"); 
	}

	@Test
	/**
	 * this method test testGetLabel() method of cryptoHomePage
	 */
	public void testGetLogo() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoHomePage = resource.adaptTo(CryptoHomePage.class);  
		assertEquals(cryptoHomePage.getLogo(), "/content/dam/CryptoCurrency/BTCIcon.png");
	}

	@Test
	/**
	 * this method test testGetAlt() method of cryptoHomePage
	 */
	public void testGetAlt() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoHomePage = resource.adaptTo(CryptoHomePage.class);  
		assertEquals(cryptoHomePage.getAlt(), "Bitcoin is a cryptocurrency, a form of electronic cash. It is a decentralized digital currency without a central bank or single administrator that can be sent from user-to-user on the peer-to-peer bitcoin network without the need for intermediaries"); 
	}

	@Test
	/**
	 * this method test testGetCta() method of cryptoHomePage
	 */
	public void testGetCta() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoHomePage = resource.adaptTo(CryptoHomePage.class);  
		assertEquals(cryptoHomePage.getCta(), "/content/CryptoCurrency/en/CryptoHome/BTCPage"); 
	}


}

