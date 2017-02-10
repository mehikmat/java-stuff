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
    private static final String SEARCH_ATTRIBUTE = "url";

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

                // We're only interested in processing the responses, not requests or metadata
                if (record.getHeader().getMimetype().equals("application/http; msgtype=response") && URL_PATTERN.matcher(siteUrl).find()) {
                    // Convenience function that reads the full message into a raw byte array
                    byte[] rawData = IOUtils.toByteArray(record, record.available());
                    String content = new String(rawData);

                    // The HTTP header gives us valuable information about what was received during the request
                    String headerText = content.substring(0, content.indexOf("\r\n\r\n"));

                    // In our task, we're only interested in text/html, so we can be a little lax
                    if (headerText.contains("Content-Type: text/html")) {
                        // Only extract the body of the HTTP response when necessary
                        String body = content.substring(content.indexOf("\r\n\r\n") + 4);

                        Document doc = Jsoup.parseBodyFragment(body);
                        Elements links = doc.select("a[href]");
                        for (Element link : links) {
                            // Process all the matched HTML tags found in the body of the document
                            if (link.attr("abs:href").contains(SEARCH_WORD)) {
                                outKey.set(new URI(siteUrl).getHost()); // extract root url
                                context.write(outKey, outVal);
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
