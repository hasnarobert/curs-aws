package cursaws.laborator3.s3;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.StringInputStream;

public class Main {

  public static final String ACCESS_KEY = "xxx";
  public static final String SECRET_KEY = "xxx";
  public static final String BUCKET_NAME = "xxx";

  public static void main(String[] args) throws Exception {
    AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    AmazonS3Client s3 = new AmazonS3Client(credentials);

    String continutMesaj = "Fisier de test";
    String prefixFisier = "fisier.test";

    PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME,
                                                             prefixFisier,
                                                             new StringInputStream(continutMesaj),
                                                             new ObjectMetadata());
    PutObjectResult putObjectResult = s3.putObject(putObjectRequest);
    System.out.println("Am salvat fisierul in S3. Fisierul are md5: " + putObjectResult.getContentMd5());

    GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET_NAME, prefixFisier);
    S3Object s3Object = s3.getObject(getObjectRequest);
    String continutFisierDinS3 = IOUtils.toString(s3Object.getObjectContent());
    System.out.println("Am citit din S3 mesajul: " + continutFisierDinS3);

    s3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, prefixFisier));
    System.out.println("Am sters fisierul din S3");
  }
}
