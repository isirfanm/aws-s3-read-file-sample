package poc.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

  public static void main(String[] args) {
    String accessKey = null;
    String secreteKey = null;

    accessKey = System.getenv("AWS_ACCESS_KEY_ID");
    secreteKey = System.getenv("AWS_SECRET_ACCESS_KEY");

    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secreteKey);
    AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
    AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
    builder.setCredentials(credentialsProvider);
    AmazonS3 s3 = builder.build();

    String bucketName = null;
    String keyName = null;

    bucketName = System.getenv("BUCKET_NAME");
    keyName = System.getenv("KEY_NAME");

    S3Object object = s3.getObject(bucketName, keyName);
    S3ObjectInputStream s3ObjectInputStream = object.getObjectContent();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s3ObjectInputStream));
    bufferedReader.lines().forEach(System.out::println);
  }
}
