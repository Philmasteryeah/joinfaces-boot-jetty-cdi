package org.philmaster.boot.controller;

import java.io.StringWriter;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.philmaster.boot.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PMRestController {

	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/xml", headers = "Accept=*/*")
	public @ResponseBody String getPerson() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(new Person("1", "Hans", 1, LocalDate.now()), sw);
		return sw.toString();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/xml", headers = "Accept=*/*")
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