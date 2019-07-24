/** 
 * The CryptoProfitModel class implements an application that
 * simply displays the profit in buying and selling the currency 
 * at different times of thhe day..
 */

package com.adobe.aem.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.settings.SlingSettingsService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;

/**
 * This is the model class to fetch all the dialog values for the Cryptoprofit Component
 *  and also calculates the profit.
 * @author Vikram
 *
 */
@Model(adaptables = Resource.class)
public class CryptoProfitModel {

	@Inject
	private SlingSettingsService settings;

	@Inject @Default(values="BTC")
	public String label;

	/**
	 * The buy Price of the currency
	 */
	public double buyPrice;

	/**
	 * The sell Price of the currency
	 */
	public double sellPrice;

	@Inject @Default(values="0945")
	public int buyTime;

	/**
	 * The buy Time of the currency
	 */
	public int sellTime;

	/**
	 * The string Sell time  of the currency
	 */
	public String strSellTime;

	/**
	 * The buy Time of the currency
	 */
	public String strBuyTime;

	/**
	 * The Profit made 
	 */
	public double profit;

	public JSONArray quotes;

	public String getLabel() {
		return label;
	}

	/**
	 * Gets the Buy Price in Double format.
	 * @return this Sell Time.
	 */
	public double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * Gets the Buy Time in Integer format.
	 * @return this Sell Time.
	 */
	public int getBuyTime() {
		return buyTime;
	}

	/**
	 * Gets the Buy Time in String format.
	 * @return this Buy Time.
	 */
	public String getStrBuyTime() {
		return strBuyTime;
	}

	/**
	 * Gets the Sell Price in Double format.
	 * @return this Sell Price.
	 */
	public double getSellPrice() {
		return sellPrice;
	}

	/**
	 * Gets the Sell Time in Integer format.
	 * @return this Sell Time.
	 */
	public int getSellTime() {
		return sellTime;
	}

	/**
	 * Gets the Sell Time in String format.
	 * @return this Sell Time.
	 */
	public String getStringSellTime() {
		return strSellTime;
	}

	/**
	 * Gets the Profit in Double format.
	 * @return this Profit.
	 */
	public double getProfit() {
		return profit;
	}

	@PostConstruct
	// PostConstructs are called after all the injection has occurred, but before the Model object is returned for use.
	protected void init() throws MalformedURLException, IOException, JSONException {

		String url = "https://raw.githubusercontent.com/Vikramjeets/NABCryptoCurrency/master/CryptoCurrency/cryptocurrencies.json";

		String jsonStr = IOUtils.toString(new URL(url));

		JSONArray jsonarray = new JSONArray(jsonStr);

		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject obj1 = jsonarray.getJSONObject(i);
			if(label.equalsIgnoreCase(obj1.getString("currency"))) {
				quotes = obj1.getJSONArray("quotes");

				// Calling getMax() method for getting max value
				double max = getMax(quotes);
				sellPrice = max;

				// Calling getMin() method for getting min value
				double min = getMin(quotes);
				buyPrice = min;

				profit = Math.round((sellPrice-buyPrice) * 100.0) / 100.0;

				StringBuilder sb = new StringBuilder(strBuyTime);
				sb.insert(2, ":");
				strBuyTime = sb.toString();
				StringBuilder sb1 = new StringBuilder(strSellTime);
				sb1.insert(2, ":");
				strSellTime =sb1.toString();
			}

		}

	}

	/**
	 * This method calculates the maximum value from the json. 
	 *  * @param JSONArray 
	 * @return maxValue
	 */	
	public  double getMax(JSONArray inputArray) throws JSONException{ 
		double maxValue = inputArray.getJSONObject(0).getDouble("price"); 
		double minValue = inputArray.getJSONObject(0).getDouble("price"); 
		sellTime = inputArray.getJSONObject(0).getInt("time");
		strSellTime = inputArray.getJSONObject(0).getString("time");


		for(int i=0;i < inputArray.length();i++){ 
			if(inputArray.getJSONObject(i).getDouble("price") > maxValue){ 
				maxValue = inputArray.getJSONObject(i).getDouble("price");
				sellTime = inputArray.getJSONObject(i).getInt("time");
				sellPrice = maxValue;
				strSellTime = inputArray.getJSONObject(i).getString("time");

			} 
		} 
		return maxValue; 
	}


	/**
	 * This method calculates the minimum value from the json. 
	 * @param JSONArray 
	 * @return minValue
	 */	
	public  double getMin(JSONArray inputArray) throws JSONException{ 
		double minValue = inputArray.getJSONObject(0).getDouble("price"); 
		buyTime = inputArray.getJSONObject(0).getInt("time");
		buyPrice = minValue;
		strBuyTime = inputArray.getJSONObject(0).getString("time");


		for(int i=0;i<inputArray.length();i++){ 
			if(( sellTime>inputArray.getJSONObject(i).getInt("time")) && ((inputArray.getJSONObject(i).getDouble("price")-buyPrice)>sellPrice-buyPrice)){
				minValue = inputArray.getJSONObject(i).getDouble("price");
				buyTime = inputArray.getJSONObject(i).getInt("time");
				strBuyTime = inputArray.getJSONObject(i).getString("time");
			} 
		} 
		return minValue; 
	} 

	/**
	 * This method gets all currency list with times from the json. 
	 * @return List<ValueMap>
	 */
	public List<ValueMap> getItems() throws Exception  {
		List<ValueMap> properties = new ArrayList<>();
		for(int j=0; j<quotes.length(); j++) {
			Map<String, Object> map = new HashMap<>();
			StringBuilder sb = new StringBuilder(quotes.getJSONObject(j).getString("time"));
			sb.insert(2, ":");
			map.put("time",sb.toString());
			map.put("price",quotes.getJSONObject(j).getString("price"));
			properties.add(new ValueMapDecorator(map));
		}
		return properties;
	}

}

