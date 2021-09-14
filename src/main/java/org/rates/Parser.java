package org.rates;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class Parser {

	public static Document buildDocument(String url) {
		
		Document document = null;
	    try {
	       SAXBuilder sb = new SAXBuilder();
	       document = sb.build(new URL(url));
	    } catch (IOException | JDOMException e) {
	        e.printStackTrace();
	    }
	    return document;
	}
	
	
	public Currencies parseDocument(Currencies currencies) {

		String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
		HashMap<String,Double> currencyMap;

		Document document = buildDocument(url);
		Namespace ns = Namespace.getNamespace("http://www.ecb.int/vocabulary/2002-08-01/eurofxref");

		LocalDate dateUpdated = updateDate(document, ns);

		if (currencies.getDateUpdated() == null) {
			currencyMap = updateRates(document, ns);
			currencies.setCurrencyMap(currencyMap);
			currencies.setDateUpdated(dateUpdated);

		} else if (dateUpdated.isAfter(currencies.getDateUpdated())) {
			currencyMap = updateRates(document, ns);
			currencies.setCurrencyMap(currencyMap);
			currencies.setDateUpdated(dateUpdated);
		}
		return currencies;
	}


	private HashMap<String,Double> updateRates(Document document, Namespace ns) {

		HashMap<String,Double> currencies = new HashMap<>();

		Element rootNode = document.getRootElement();
		Element cube = rootNode.getChild("Cube", ns).getChild("Cube", ns);
		List<Element> cubeList = cube.getChildren("Cube", ns);

		for (Element element : cubeList) {
			String currency = element.getAttributeValue("currency");
			Double rate = Double.parseDouble(element.getAttributeValue("rate"));
			currencies.put(currency, rate);
		}
		return currencies;
	}


	private LocalDate updateDate(Document document, Namespace ns) {

		Element rootNode = document.getRootElement();
		Element cube = rootNode.getChild("Cube", ns).getChild("Cube", ns);
		String dateString = cube.getAttributeValue("time");

		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
