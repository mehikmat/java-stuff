
Common Crawls File Types
-------------------------

1. WARC (Web Archive Commons) File:
   Files which store the raw crawl data.

2. WAT(WARC Meta Data) File:
   Files which store computed metadata for the data stored in the WARC.
   It contains target url and all the links in that target url page.

3. WET(WARC Extracted Text) File:
   Files which store extracted plaintext from the data stored in the WARC.

Our requirements:
-----------------
Using the Common Crawl latest public datasets deliver to me a list of root URLs for websites that within the site
hierarchy has a URL containing the word “contact”. Also these websites must be of .org, .com or .net domain extentions
and the root URL must not contain more than one non-alphanumeric character (for example, don’t include results like “www.my--site.com”).

Input File selection for Solution:
----------------------------------
So we can achieve required output using WAT files which is small in comparison to WARC files.


Download latest WAT file from s3
--------------------------------
$> aws s3 cp s3://commoncrawl/crawl-data/CC-MAIN-2016-50/segments/1480698544679.86/wat/CC-MAIN-20161202170904-00461-ip-10-31-129-80.ec2.internal.warc.wat.gz /data/

Here I am using only one file for local processing in single node hadoop cluster.
But we can use all files and process in large hadoo cluster.

Reference taken from https://github.com/rossf7/wikireverse for WARC File InputFormat.

$> hadoop fs -mkdir -p cc_input
$> hadoop fs -put /data/CC-MAIN-20161202170904-00461-ip-10-31-129-80.ec2.internal.warc.wat.gz cc_input/

Run jar
--------

$> git clone https://github.com/mehikmat/java-stuffs
$> cd java-stuffs/WatUrlExtractor
$> mvn clean package

$> hadoop jar target/CCUrlExtractor-1.0.jar

see output at cc_output_merged file.


