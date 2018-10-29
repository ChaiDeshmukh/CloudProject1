package com.filezila.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
//import javax.annotation.PostConstruct;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class AmazonClientService {

	private AmazonS3 s3client;
	private String awsS3Bucket;
	private String endpointUrl;

	private static final String SUFFIX = "/";

	// to execute after dependency injection is done

	@Autowired
	public AmazonClientService(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3Bucket,
			String endpointUrl) {
		this.s3client = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(awsRegion.getName()).build();
		this.awsS3Bucket = awsS3Bucket;
		this.endpointUrl = endpointUrl;
	}

	public String uploadFile(MultipartFile multipartFile) {
		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			// System.out.println("Endpoint URL:"+endpointUrl);
			fileUrl = endpointUrl + "/" + awsS3Bucket + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
		// return "file is uploaded successfully";
	}

	public File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	public void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(awsS3Bucket, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	
	public String deleteFileFromS3Bucket(String fileName, String userName) {
		//trustAllHosts();
		//String folderName =firstName;
		//String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		//String keyName = folderName + "/" + fileName ;
		//s3client.deleteObject(new DeleteObjectRequest(awsS3Bucket + "/", keyName));
		System.out.println("@*&#&#^#^#@%@@$@$@$@$@@#$@#@#@#@#@#@#");
		s3client.deleteObject(new DeleteObjectRequest(awsS3Bucket, userName +"/"+fileName));
		return "Successfully deleted";
	}
	

	public String uploadSampleFile(MultipartFile multipartFile, String userName) {
		
		String folderName = userName;
		createFolder(awsS3Bucket, folderName, s3client);
		try {
			File s3File = convertMultiPartToFile(multipartFile);

			// String keyName = userName +"_"+s3File.getName();
			String keyName = folderName + "/" + s3File.getName();
			// s3client.putObject(awsS3Bucket, keyName, s3File);
			s3client.putObject(new PutObjectRequest(awsS3Bucket, keyName, s3File)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			return endpointUrl + "/" + awsS3Bucket + "/" +keyName;
			
		} catch (Exception e) {

			throw new RuntimeException("FAIL!");
		}

	}

	public byte[] downloadFile(String fileName, String userName) {
		try {
			String folderName = userName;
			String keyName = folderName + "/" + fileName;
			S3Object s3Object = s3client.getObject(new GetObjectRequest(awsS3Bucket, keyName));
			S3ObjectInputStream inputStream = s3Object.getObjectContent();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			System.out.println(")@*#*#&*HDGD#DVHDBDGD^E = "+bytes);
			return bytes;

		} catch (IOException e) {

			throw new RuntimeException("FAIL!");
		}

	}

	public static void createFolder(String awsS3Bucket, String folderName, AmazonS3 s3client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3Bucket, folderName + SUFFIX, emptyContent,
				metadata);
		// send request to S3 to create folder
		s3client.putObject(putObjectRequest);
	}

}
