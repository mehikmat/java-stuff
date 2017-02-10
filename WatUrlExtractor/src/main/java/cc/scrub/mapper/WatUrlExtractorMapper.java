package cc.scrub.mapper;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;
import org.archive.io.ArchiveReader;
import org.archive.io.ArchiveRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;


/**
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 */
public class WatUrlExtractorMapper extends Mapper<Text, ArchiveReader, Text, NullWritable> {
    private static final Logger LOG = Logger.getLogger(WatUrlExtractorMapper.class);

    private static final String RGX_URL = "^(http[s]?:\\/\\/)?(www[.])?[a-z0-9]+([\\-][a-z0-9]+)?(\\.com|\\.net|\\.org)([.][a-z]{1,3})?((?=\\/).*)?$";
    private static final String SEARCH_WORD = "contact";

    private Text outKey = new Text();
    private NullWritable outVal = NullWritable.get();
    private Pattern URL_PATTERN = null;

    @Override
    public void map(Text key, ArchiveReader archiveRecords, Context context) throws IOException {
        // compile once for each file and use for all records
        URL_PATTERN = Pattern.compile(RGX_URL);

        for (ArchiveRecord record : archiveRecords) {
            try {
                String siteUrl = record.getHeader().getUrl();

                // check record type if it's response, and not request or metadata and target url matches our criteria
                if (record.getHeader().getMimetype().equals("application/http; msgtype=response") && URL_PATTERN.matcher(siteUrl).find()) {

                    // convert to raw bytes
                    byte[] rawData = IOUtils.toByteArray(record, record.available());
                    String content = new String(rawData);

                    // Extract the HTTP header
                    String headerText = content.substring(0, content.indexOf("\r\n\r\n"));

                    // Extract the page body as we check for at least one link having word `contact`
                    if (headerText.contains("Content-Type: text/html")) {
                        String body = content.substring(content.indexOf("\r\n\r\n") + 4);

                        // parse using jsoup
                        Document doc = Jsoup.parseBodyFragment(body);
                        Elements links = doc.select("a[href]");

                        for (Element link : links) {
                            if (link.attr("abs:href").contains(SEARCH_WORD)) {
                                outKey.set(new URI(siteUrl).getHost()); // extract root url
                                context.write(outKey, outVal);
                                // once we find the link having word 'contact' break and output record's site url
                                break;
                            }
                        }
                    }
                }
            }catch (Exception ex) {
                LOG.error("Caught Exception", ex);
            }
        }
    }
}
