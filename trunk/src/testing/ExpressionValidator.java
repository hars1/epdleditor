package testing;

import java.io.File;
import java.io.IOException;

import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ExpressionValidator {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws IOException, SAXException {

        try {

            // Parse an XML document into a DOM tree.
            DocumentBuilder parser =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document =  parser.parse(new File("src/EPDLEditor/XML/myXMLDocument.xml"));

            // Create a SchemaFactory capable of understanding WXS schemas.
            SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Load a WXS schema, represented by a Schema instance.
            Source schemaFile = new StreamSource(new File("src/EPDLEditor/XML/ExpressionBuilder.xsd"));
            Schema schema = factory.newSchema(schemaFile);

            // Create a Validator object, which can be used to validate
            // an instance document.
            Validator validator = schema.newValidator();

            // Validate the DOM tree.
            validator.validate(new DOMSource( document));

        } catch (ParserConfigurationException e) {
            System.err.print("exception handling - parser");
        } catch (SAXException e) {
        	e.printStackTrace();
        } catch (IOException e) {
            System.err.print("exception handling - IO");
        }       

 
	}

}
