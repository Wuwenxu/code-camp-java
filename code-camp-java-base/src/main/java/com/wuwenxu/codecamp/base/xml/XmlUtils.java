

package com.wuwenxu.codecamp.base.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: speech_recognition_be
 * @description: this is the description of the XmlUtils class
 * @author: ahuan
 * @version: 2020-06-15 16:18
 **/
public final class XmlUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    public XmlUtils() {
    }

    public static XMLReader getXmlReader() {
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            reader.setFeature("http://xml.org/sax/features/namespaces", true);
            reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            return reader;
        } catch (Exception var1) {
            throw new RuntimeException("Unable to create XMLReader", var1);
        }
    }

    public static List<String> getTextForElements(String xmlAsString, final String element) {
        final List<String> elements = new ArrayList(2);
        XMLReader reader = getXmlReader();
        DefaultHandler handler = new DefaultHandler() {
            private boolean foundElement = false;
            private StringBuilder buffer = new StringBuilder();
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }

            }
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = false;
                    elements.add(this.buffer.toString());
                    this.buffer = new StringBuilder();
                }

            }
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (this.foundElement) {
                    this.buffer.append(ch, start, length);
                }

            }
        };
        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
            return elements;
        } catch (Exception var6) {
            LOGGER.error(var6.getMessage(), var6);
            return null;
        }
    }

    public static String getTextForElement(String xmlAsString, final String element) {
        XMLReader reader = getXmlReader();
        final StringBuilder builder = new StringBuilder();
        DefaultHandler handler = new DefaultHandler() {
            private boolean foundElement = false;
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }

            }
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = false;
                }

            }
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (this.foundElement) {
                    builder.append(ch, start, length);
                }

            }
        };
        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
        } catch (Exception var6) {
            LOGGER.error(var6.getMessage(), var6);
            return null;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String str="<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>\n" +
                "    <cas:authenticationSuccess>\n" +
                "        <cas:user>13456567878</cas:user>\n" +
                "        <cas:attributes>\n" +
                "            <cas:ORG_ID>65da35050562490b8ba9148707fe92b5</cas:ORG_ID>\n" +
                "            <cas:isFromNewLogin>false</cas:isFromNewLogin>\n" +
                "            <cas:authenticationDate>2020-06-13T15:04:51.110+08:00[Asia/Shanghai]</cas:authenticationDate>\n" +
                "            <cas:ORG_NAME>应急管理部/应急测试</cas:ORG_NAME>\n" +
                "            <cas:ORG_FULL_NAME>应急测试机构</cas:ORG_FULL_NAME>\n" +
                "            <cas:successfulAuthenticationHandlers>PWD</cas:successfulAuthenticationHandlers>\n" +
                "            <cas:USER_NAME>八月底九</cas:USER_NAME>\n" +
                "            <cas:credentialType>UsernamePasswordCredential</cas:credentialType>\n" +
                "            <cas:authenticationMethod>PWD</cas:authenticationMethod>\n" +
                "            <cas:IDCARD_UUID>cb616dc779d84cbdb77d1a590c73edb2</cas:IDCARD_UUID>\n" +
                "            <cas:IDCARD_NO>153431189109124911</cas:IDCARD_NO>\n" +
                "            <cas:clientIP>11.125.12.3</cas:clientIP>\n" +
                "            <cas:longTermAuthenticationRequestTokenUsed>false</cas:longTermAuthenticationRequestTokenUsed>\n" +
                "            </cas:attributes>\n" +
                "    </cas:authenticationSuccess>\n" +
                "</cas:serviceResponse>\n";

        String userId = XmlUtils.getTextForElement(str, "user");
        String userName = XmlUtils.getTextForElement(str, "USER_NAME");
        String idcardUuid = XmlUtils.getTextForElement(str, "IDCARD_UUID");
        System.out.println(userId+" "+userName+" "+idcardUuid);
    }
}
