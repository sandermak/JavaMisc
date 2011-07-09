package nl.jpoint.javaseven.b.exceptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MultiCatch {

    private static final Logger LOGGER = Logger.getLogger(MultiCatch.class.getName());

    public static void main(String[] args) throws Exception {
        MultiCatch c = new MultiCatch();
        if (c.hasXmlNode()) {
            System.out.println("Succes!");
        }
    }

    boolean hasXmlNode() {

        try {
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document doc = builder.parse(new FileInputStream("xml.xml"));
            final XPathExpression expr = XPathFactory.newInstance().newXPath().compile("//element");
            expr.evaluate(doc, XPathConstants.NODESET);

        } catch (final ParserConfigurationException e) {
            //Log and return false:
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        } catch (final SAXException e) {
            //Log and return false:
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        } catch (final IOException e) {
            //Log and return false:
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        } catch (final XPathExpressionException e) {
            //Log and return false:
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        }
        return true;
    }

    boolean hasXmlNodeInJava7() {
        try {
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document doc = builder.parse(new FileInputStream(""));
            final XPathExpression expr = XPathFactory.newInstance().newXPath().compile("some expression");
            expr.evaluate(doc, XPathConstants.NODESET);

        } catch (final ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            //Log and return false:
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        }
        return true;
    }
}
