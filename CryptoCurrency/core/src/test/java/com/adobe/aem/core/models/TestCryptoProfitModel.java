
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

import com.adobe.aem.core.models.CryptoProfitModel;

/**
 * This class tests CryptoProfitModel
 * 
 * @author Vikram
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCryptoProfitModel {

	//@Inject
	private CryptoProfitModel cryptoProfitModel;

	private Resource resource;
	private final String myPagePath = "/content/CryptoCurrency/en/CryptoHome/ETHPage";

	@Rule
	public final SlingContext context = new SlingContext(ResourceResolverType.JCR_MOCK); 

	@Before
	/**
	 * This method takes care of initial set up of data
	 * 
	 */
	public void setUp() throws Exception {            
		context.load().json( "/content.json", myPagePath);
		context.addModelsForPackage(getClass().getPackage().getName());
	}

	@Test
	/**
	 * this method test testGetLabel() method of cryptoProfitModel
	 */
	public void testGetLabel() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getLabel(), "BTC"); 
	}

	@Test
	/**
	 * this method test testGetBuyPrice() method of cryptoProfitModel
	 */
	public void testGetBuyPrice() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getBuyPrice(), 34.98, 0);
	}

	@Test
	/**
	 * this method test testGetBuyTime() method of cryptoProfitModel
	 */
	public void testGetBuyTime() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getBuyTime(), 915); 
	}

	@Test
	/**
	 * this method test testGetSellPrice() method of cryptoProfitModel
	 */
	public void testGetSellPrice() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getSellPrice(),37.01 , 0); 
	}

	@Test
	/**
	 * this method test testGetSellTime() method of cryptoProfitModel
	 */
	public void testGetSellTime() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getSellTime(), 1230); 
	}

	@Test
	/**
	 * this method test testGetProfit() method of cryptoProfitModel
	 */
	public void testGetProfit() throws Exception {
		resource = context.resourceResolver()
				.getResource(myPagePath);
		cryptoProfitModel = resource.adaptTo(CryptoProfitModel.class);  
		assertEquals(cryptoProfitModel.getProfit(),2.03, 1); 
	}


}
