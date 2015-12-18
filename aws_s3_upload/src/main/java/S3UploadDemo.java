import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by hdhamee on 12/18/15.
 */
public class S3UploadDemo {
    public static void main(String[] args) throws IOException {
        uploadToS3("local_file_path");
        uploadToS3FromHDFS("hdfs_folder_paht");
    }

    public static void uploadToS3(String localFilePath) throws IOException {
        /**
         * Multipart uploading is a three-step process: You initiate the upload,
         * you upload the object parts, and after you have uploaded all the parts,
         * you complete the multipart upload. Upon receiving the complete multipart upload request
         * Amazon S3 constructs the object from the uploaded parts, and you can then access
         * the object just as you would any other object in your bucket.
         */

        /**
         * Concurrent Multipart Upload Operations
         * In a distributed development environment, it is possible for your application to initiate
         * several updates on the same object at the same time. Your application might initiate several
         * multipart uploads using the same object key. For each of these uploads, your application
         * can then upload parts and send a complete upload request to Amazon S3 to create the object
         * . When the buckets have versioning enabled, completing a multipart upload always creates a new version.
         * For buckets that do not have versioning enabled, it is possible that some other request
         * received between the time when a multipart upload is initiated and when it is
         * completed might take precedence.
         */

        // aws keys
        String accessKey = "";
        String secretKey = "";

        // create aws client
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = new AmazonS3Client(credentials);
        List<PartETag> partETags = new ArrayList<PartETag>();

        // Step 1: Initialize multi-part upload
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(
                "testbucket", "test");
        InitiateMultipartUploadResult initResponse =
                s3Client.initiateMultipartUpload(initRequest);
        // this upload ID for each subsequent multipart upload operation
        String uploadId =  initResponse.getUploadId();

        // Step 2 : Upload part. For each part execute uploadPart method
        File file = new File(localFilePath);
        long contentLength = file.length();
        long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
        long filePosition = 0;

        for(int i = 1; filePosition < contentLength; ++i) {
            // Last part can be less than 5 MB. Adjust part size.
            partSize = Math.min(partSize, (contentLength - filePosition));

            // Create request to upload a part.
            UploadPartRequest uploadRequest = new UploadPartRequest()
                    .withBucketName("testbucket").withKey("test")
                    .withUploadId(initResponse.getUploadId()).withPartNumber(i)
                    .withFileOffset(filePosition)
                    .withFile(file)
                    .withPartSize(partSize);
            // Upload part and add response to our list.
            partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());
            filePosition += partSize;

        }

        //Step: 3 Complete request
        CompleteMultipartUploadRequest compRequest = new
                CompleteMultipartUploadRequest("testbucket",
                "test",
                uploadId,
                partETags);
        // merge upload parts
        s3Client.completeMultipartUpload(compRequest);

        //delete local copy after upload completes
        file.deleteOnExit();
    }

    public static void uploadToS3FromHDFS(String hdfsFolderPath){
        String S3_CONFIG_FILE_NAME = "s3_config.properties";
        String BUCKET_NAME_PARAM = "aws_bucket_name";
        String ACCESS_KEY_PARAM = "aws_access_key";
        String SECRET_KEY_PARAM = "aws_secret_key";
        String FOLDER_NAME = "Test";
        String SUFFIX = "/";
        String FILE_NAME = FOLDER_NAME + SUFFIX + hdfsFolderPath.split("/")[hdfsFolderPath.split("/").length-1];
        /**
         * Multipart uploading is a three-step process: You initiate the upload,
         * you upload the object parts, and after you have uploaded all the parts,
         * you complete the multipart upload. Upon receiving the complete multipart upload request
         * Amazon S3 constructs the object from the uploaded parts, and you can then access
         * the object just as you would any other object in your bucket.
         */

        /**
         * Concurrent Multipart Upload Operations
         * In a distributed development environment, it is possible for your application to initiate
         * several updates on the same object at the same time. Your application might initiate several
         * multipart uploads using the same object key. For each of these uploads, your application
         * can then upload parts and send a complete upload request to Amazon S3 to create the object
         * . When the buckets have versioning enabled, completing a multipart upload always creates a new version.
         * For buckets that do not have versioning enabled, it is possible that some other request
         * received between the time when a multipart upload is initiated and when it is
         * completed might take precedence.
         */

        // load s3 config
        Properties s3Config = PropertiesLoader.loadProperties(S3_CONFIG_FILE_NAME);

        // aws keys
        String accessKey = s3Config.getProperty(ACCESS_KEY_PARAM);
        String secretKey = s3Config.getProperty(SECRET_KEY_PARAM);
        String bucket_name = s3Config.getProperty(BUCKET_NAME_PARAM);

        // create aws client
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = new AmazonS3Client(credentials);

        // create folder
        createFolder(bucket_name,FOLDER_NAME,SUFFIX,s3Client);

        // for storing uploaded part ids
        List<PartETag> partETags = new ArrayList<PartETag>();

        // Step 1: Initialize multi-part upload
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(
                bucket_name, FILE_NAME);
        InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
        // This upload ID for each subsequent multipart upload operation
        String uploadId = initResponse.getUploadId();

        // creaet hadoop file system reference
        Configuration configuration = new Configuration();
        FileSystem fileSystem = null;

        try {
            fileSystem = FileSystem.get(configuration);
            FileStatus[] fileStatuses = fileSystem.listStatus(new Path(hdfsFolderPath));

            int i = 1;
            File file = null;
            Writer writer = null;
            boolean flag = false;
            int numFiles = fileStatuses.length;

            // Step 2 : Upload part. For each part execute uploadPart method
            for (FileStatus fileStatus : fileStatuses) {
                if (!flag) {
                    file = File.createTempFile("aws-temp-file", ".csv");
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                }
                if (fileStatus.getPath().getName().startsWith("part")) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(fileSystem.open(fileStatus.getPath())));
                    String line;
                    while ((line = br.readLine()) != null) {
                        writer.write(line + "\n");
                    }
                }
                numFiles--;
                // Last part can be less than 5 MB. Adjust part size.
                if (file.length() < (10 * 1024 * 1024) && numFiles > 0) {
                    flag = true;
                    continue;
                }
                writer.close();
                System.out.println("\n[Progress: ]Uploading part:" + i + " size: " + file.length()/(1024*1024) + "\n");
                long partSize = file.length();

                // Create request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(bucket_name).withKey(FILE_NAME)
                        .withUploadId(initResponse.getUploadId()).withPartNumber(i)
                        .withFileOffset(0)
                        .withFile(file)
                        .withPartSize(partSize);
                // Upload part and add response to our list.
                partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());
                i++;
                file.delete();
                flag = false;
            }
        } catch (IOException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(e.getLocalizedMessage());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }


        //Step:3  Complete request, aws will merge all the parts to single object
        CompleteMultipartUploadRequest compRequest = new
                CompleteMultipartUploadRequest(bucket_name,
                FILE_NAME,
                uploadId,
                partETags);
        s3Client.completeMultipartUpload(compRequest);
    }

    public static void createFolder(String bucketName, String folderName, String SUFFIX, AmazonS3 client) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName + SUFFIX, emptyContent, metadata);
        // send request to S3 to create folder
        client.putObject(putObjectRequest);
    }

    private static class PropertiesLoader{
        public static Properties loadProperties(String fileName) {
            Properties prop = new Properties();
            if(fileName != null && !fileName.isEmpty()) {
                try {
                    if(fileName.startsWith("classPath:")) {
                        InputStream e = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName.substring("classPath:".length()));
                        prop.load(e);
                    } else {
                        FileInputStream e1 = new FileInputStream(fileName);
                        DataInputStream in = new DataInputStream(e1);
                        prop.load(in);
                    }

                    return prop;
                } catch (IOException var4) {
                    throw new RuntimeException(var4);
                }
            } else {
                return prop;
            }
        }
    }
}
