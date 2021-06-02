package com.example.bogvaluteconverter.util;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class NbgRssParser implements RssParser {

    private List<String> country;
    private List<String>  list;

    public static Document loadXMLFromString(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    @Override
    public BigDecimal getCurrencyRate(String currency) {

        URL feedSource = null;
        try {
            feedSource = new URL("http://www.nbg.ge/rss.php");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            String currencies = ((SyndEntryImpl)(feed.getEntries().get(0))).getDescription().getValue();
            System.out.println(currencies);
//            Document doc = loadXMLFromString(currencies);
//            System.out.println(doc.getDocumentElement());
//            System.out.println(doc.getElementsByTagName("table"));
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
        getData();
        System.out.println(country);
        System.out.println(list);
        return BigDecimal.ONE;
    }

    private void getData() {
        try {
            URL url = new URL("http://www.nbg.ge/rss.php");
            InputStream in = url.openConnection().getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(in, "UTF-8");
            int countDescription = 3;
            String descriptionText = "";
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equalsIgnoreCase("description")) {
                        countDescription--;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (countDescription == 1) {
                        descriptionText = parser.getText();
                        countDescription--;
                    }
                }
                eventType = parser.next();
            }

            StringTokenizer st = new StringTokenizer(descriptionText, "</>");
            boolean first = true;
            while (st.hasMoreTokens()) {
                String next = st.nextToken();
                if (next.equals("td")) {
                    if (first) {
                        list.add(st.nextToken());
                    }
                    first = !first;
                }
            }
            System.out.println(list);
            for (int i = 0; i < list.size(); i += 5) {
                country.add(list.get(i));
            }
            in.close();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
