package com.nauticana.finance.service;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class FinanceService {

	public void loadLiraRate() {
		URL xmlURL;
		try {
			xmlURL = new URL("http://www.tcmb.gov.tr/kurlar/today.xml");
			InputStream xml = xmlURL.openStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("Currency");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				element.getAttribute("CurrencyCode");
				getValueofElement(element,"CurrencyName");
				getValueofElement(element,"BanknoteBuying");
				getValueofElement(element,"BanknoteSelling");
				getValueofElement(element,"ForexBuying");
				getValueofElement(element,"ForexSelling");
				getValueofElement(element,"Unit");
			}
			xml.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getValueofElement(Element parentElement, String label) {
		String retval="";
		Element requiredElement=(Element) parentElement.getElementsByTagName(label).item(0);
		try {
			Node child = requiredElement.getFirstChild();
			if (child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				retval= cd.getData();
			} else {
				// donothing
				//retval=””;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}
}
