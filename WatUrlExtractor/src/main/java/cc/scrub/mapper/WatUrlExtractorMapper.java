package cc.scrub.mapper;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;
import org.archive.io.ArchiveReader;
import org.archive.io.ArchiveRecord;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;


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

    @Override
    public void map(Text key, ArchiveReader value, Context context) throws IOException {

        for (ArchiveRecord record : value) {
            // Skip any records that are not JSON
            if (!record.getHeader().getMimetype().equals("application/json")) {
                continue;
            }
            try {
                // Convenience function that reads the full message into a raw byte array
                byte[] rawData = IOUtils.toByteArray(record, record.available());
                String content = new String(rawData);

                JSONObject json = new JSONObject(content);

                try {
                    String siteUrl = json.getJSONObject("Envelope").getJSONObject("WARC-Header-Metadata").getString("WARC-Target-URI");
                    //JSON xpath = [Envelope']['Payload-Metadata']['HTTP-Response-Metadata']['HTML-Metadata']['Links']
                    JSONArray linksJsonArray = json.getJSONObject("Envelope").getJSONObject("Payload-Metadata").getJSONObject("HTTP-Response-Metadata")
                            .getJSONObject("HTML-Metadata").getJSONArray("Links");

                    if (siteUrl.matches(RGX_URL) && searchWord(linksJsonArray,SEARCH_ATTRIBUTE,SEARCH_WORD)) {
                        outKey.set(new URI(siteUrl).getHost()); // extract root url
                        context.write(outKey, outVal);
                    }
                } catch (JSONException ex) {
                    LOG.error(ex.getLocalizedMessage());
                }
            } catch (Exception ex) {
                LOG.error("Caught Exception", ex);
            }
        }
    }

    private boolean searchWord(JSONArray searchSpaceArray,String attribute,String searchWord)throws JSONException {
        boolean found = false;
        for (int i = 0; i < searchSpaceArray.length(); i++) {
            if(((JSONObject)(searchSpaceArray.get(i))).getString(attribute).contains(searchWord)){
                found = true;
                break;
            }
        }
        return found;
    }
}
