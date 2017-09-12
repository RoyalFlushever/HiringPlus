package com.tdt.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import org.apache.log4j.Logger;

@RestController
public class UploadController {

    private static final Logger LOGGER = Logger.getLogger(UploadController.class);

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @RequestMapping(method = RequestMethod.GET, value = "/sign-s3", produces = "application/json")
    public SignS3Response signS3(@RequestParam("fileName") String fileName) {

        try {
            LOGGER.debug("Generating pre-signed URL.");

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucket, fileName);
            generatePresignedUrlRequest.setMethod(HttpMethod.PUT);

            URL signedUrl = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
            String contentUrl = "https://" + bucket + ".s3.amazonaws.com/" + fileName;

            System.out.println("Pre-Signed URL = " + signedUrl.toString());
            return new SignS3Response(signedUrl.toString(), contentUrl);
        } catch (AmazonServiceException exception) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon S3, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message: " + exception.getMessage());
            System.out.println("HTTP  Code: "    + exception.getStatusCode());
            System.out.println("AWS Error Code:" + exception.getErrorCode());
            System.out.println("Error Type:    " + exception.getErrorType());
            System.out.println("Request ID:    " + exception.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        throw new RuntimeException("Failed to generate an S3 presigned URL request");
    }

    @Data
    private static final class SignS3Response {
        private final String signedRequest;
        private final String url;
    }

}
