package cc.scrub.driver;

import cc.scrub.mapper.WatUrlExtractorMapper;
import cc.scrub.reducer.WatUrlExtractorReducer;
import cc.scrub.thirdparty.warc.io.WARCFileInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

/**
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 */
public class WatUrlExtractorApp {
    private static final Logger LOG = Logger.getLogger(WatUrlExtractorApp.class);

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(WatUrlExtractorApp.class);

        //inputPath = s3://commoncrawl/crawl-data/CC-MAIN-2016-50/segments/1480698544679.86/wat/CC-MAIN-20161202170904-00461-ip-10-31-129-80.ec2.internal.warc.wat.gz
        String inputPath = args[0];
        String outputPath = args[1];

        LOG.info("Input path: " + inputPath);
        LOG.info("Output path: " + outputPath);

        FileInputFormat.addInputPath(job, new Path(inputPath));

        FileSystem fs = FileSystem.newInstance(conf);
        if (fs.exists(new Path(outputPath))) {
            fs.delete(new Path(outputPath), true);
        }

        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setInputFormatClass(WARCFileInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setMapperClass(WatUrlExtractorMapper.class);
        job.setReducerClass(WatUrlExtractorReducer.class);

        job.setSpeculativeExecution(false);

        job.waitForCompletion(true);

        // merge all output parts to single file
        if (fs.exists(new Path(outputPath + "_merged"))) {
            fs.delete(new Path(outputPath + "_merged"), true);
        }
        FileUtil.copyMerge(fs, new Path(outputPath), fs, new Path(outputPath + "_merged"), true, conf, null);
    }
}
