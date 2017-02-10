
Common Crawls File Types
-------------------------

1. WARC (Web Archive Commons) File:
   Files which store the raw crawl data.
   Not only does the format store the HTTP response from the websites it contacts (WARC-Type: response),
   it also stores information about how that information was requested (WARC-Type: request)
   and metadata on the crawl process itself (WARC-Type: metadata).

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

Amend: Don't include any sub domains

Input File selection for Solution:
----------------------------------
So now we are going to use WARC files though WET files are small in comparision to WARC file.


Logic Testing:
--------------

- Download latest WAT file from s3

`$> aws s3 cp  aws s3 cp s3://commoncrawl/crawl-data/CC-MAIN-2016-50/segments/1480698544679.86/warc/CC-MAIN-20161202170904-00507-ip-10-31-129-80.ec2.internal.warc.gz /data/`

Here I am using only one file for local processing in single node hadoop cluster.
But we can use all files and process in large hadoo cluster.

Reference taken from https://github.com/rossf7/wikireverse for WARC File InputFormat.

```
$> hadoop fs -mkdir -p cc_input
$> hadoop fs -put /data/CC-MAIN-20161202170904-00507-ip-10-31-129-80.ec2.internal.warc.gz cc_input/
```

Run jar
--------
```
$> git clone https://github.com/mehikmat/java-stuffs
$> cd java-stuffs/WarcUrlExtractor
$> mvn clean package

$> hadoop jar target/CCUrlExtractor-1.0.jar -input cc_input/*.warc.gz  -output cc_output

OR

For large processing

$> $> hadoop jar target/CCUrlExtractor-1.0.jar -input s3://commoncrawl/crawl-data/CC-MAIN-2016-50/segments/1480698544679.86/warc/*.warc.gz  -output cc_output

There are almost 512 files in ../warc dir in s3 each of ~1GB

```
So job will take all the .warc files in cc_input directory.

We can limit number files using path filter and also can take directly from s3 too.

See output at cc_output_merged file.

I used only one file for testing but with lareger cluster we can process all.

512*1GB ~512GB


