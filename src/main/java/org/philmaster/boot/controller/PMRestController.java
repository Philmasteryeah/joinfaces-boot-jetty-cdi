package org.philmaster.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PMRestController {

	@RequestMapping(value = "/test/person", method = RequestMethod.GET, produces = "application/xml", headers = "Accept=*/*")
	public @ResponseBody String getPerson() {

		return "person";
	}

	@RequestMapping(value = "/test/test", method = RequestMethod.GET, produces = "application/xml", headers = "Accept=*/*")
	public String test() {
		System.err.println("test");
		return "<?xmlss version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "\r\n" + "<shiporder orderid=\"889923\"\r\n"
				+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n"
				+ "xsi:noNamespaceSchemaLocation=\"shiporder.xsd\">\r\n" + "  <orderperson>John Smith</orderperson>\r\n"
				+ "  <shipto>\r\n" + "    <name>Ola Nordmann</name>\r\n" + "    <address>Langgt 23</address>\r\n"
				+ "    <city>4000 Stavanger</city>\r\n" + "    <country>Norway</country>\r\n" + "  </shipto>\r\n"
				+ "  <item>\r\n" + "    <title>Empire Burlesque</title>\r\n" + "    <note>Special Edition</note>\r\n"
				+ "    <quantity>1</quantity>\r\n" + "    <price>10.90</price>\r\n" + "  </item>\r\n" + "  <item>\r\n"
				+ "    <title>Hide your heart</title>\r\n" + "    <quantity>1</quantity>\r\n"
				+ "    <price>9.90</price>\r\n" + "  </item>\r\n" + "</shiporder>";
	}

}
