package ge.bog.currencyconverter.util;

import ge.bog.currencyconverter.model.CurrencyInfo;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Component
@Slf4j
public class NbgRssParser implements RssParser {

    private static final String NATIONAL_BANK_GEORGIA_URL = "http://www.nbg.ge/rss.php";
    private static final long REFRESH_TIME = 1L; // refresh currency info in minute
    private final Map<String, CurrencyInfo> info;
    private LocalDateTime time;

    public NbgRssParser() {
        info = new HashMap<>();
        time = LocalDateTime.now().minus(REFRESH_TIME, ChronoUnit.MINUTES);
    }


    @Override
    public Optional<CurrencyInfo> getCurrencyRate(String currency) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(time.plus(REFRESH_TIME, ChronoUnit.MINUTES))) {
            if (getNewCurrencyRates()) {
                time = LocalDateTime.now();
                return Optional.ofNullable(info.get(currency));
            }
        } else {
            return Optional.ofNullable(info.get(currency));
        }
        return Optional.empty();
    }

    private boolean getNewCurrencyRates() {
        try {
            URL feedSource = new URL(NATIONAL_BANK_GEORGIA_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            String currencies = ((SyndEntryImpl) (feed.getEntries().get(0))).getDescription().getValue();
            String stringWithOutImageTags = RemoveImageTags(currencies);
            getInfo(stringWithOutImageTags);
            return true;
        } catch (Exception e) {
            log.error("error occurred while parsing RSS");
            e.printStackTrace();
            return false;
        }

    }

    // removes image tag and table tag
    private String RemoveImageTags(String curr) {
        String starting = "<td><img";
        String ending = "></td>";
        int starting_index = curr.indexOf("<tr>");
        StringBuilder sb = new StringBuilder();
        int index = curr.indexOf(starting);
        while (index != -1) {
            sb.append(curr, starting_index, index);
            starting_index = curr.indexOf(ending, index) + 10;
            index = curr.indexOf(starting, starting_index);
        }
        sb.append("</tr>");
        return sb.toString();
    }

    // returns currency info from description xml tag
    List<String> getCurrencyList(String descriptionText) {
        List<String> list = new ArrayList<>();
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
        return list;
    }

    private void getInfo(String descriptionText) {
        List<String> list = getCurrencyList(descriptionText);

        for (int i = 0; i < list.size(); i += 4) {
            CurrencyInfo currencyInfo = new CurrencyInfo();
            currencyInfo.setName(list.get(i));
            int index = list.get(i + 1).strip().indexOf(" ");
            currencyInfo.setQuantity(Long.valueOf(list.get(i + 1).substring(0, index)));
            currencyInfo.setDescription(list.get(i + 1).substring(index + 1));
            currencyInfo.setVal(BigDecimal.valueOf(Double.parseDouble(list.get(i + 2))));
            info.put(list.get(i), currencyInfo);
        }
    }
}
