package com.yj.bishe.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudStorageConfig {

	 @Value("${oss.qiniu.domain}")
	    private String qiniuDomain;
	     
	    @Value("${oss.qiniu.accessKey}")
	    private String qiniuAccessKey;
	     
	    @Value("${oss.qiniu.secretKey}")
	    private String qiniuSecretKey;
	    
	    @Value("${oss.qiniu.bucketName}")
	    private String qiniuBucketName;

		public String getQiniuDomain() {
			return qiniuDomain;
		}

		public void setQiniuDomain(String qiniuDomain) {
			this.qiniuDomain = qiniuDomain;
		}

		public String getQiniuAccessKey() {
			return qiniuAccessKey;
		}

		public void setQiniuAccessKey(String qiniuAccessKey) {
			this.qiniuAccessKey = qiniuAccessKey;
		}

		public String getQiniuSecretKey() {
			return qiniuSecretKey;
		}

		public void setQiniuSecretKey(String qiniuSecretKey) {
			this.qiniuSecretKey = qiniuSecretKey;
		}

		public String getQiniuBucketName() {
			return qiniuBucketName;
		}

		public void setQiniuBucketName(String qiniuBucketName) {
			this.qiniuBucketName = qiniuBucketName;
		}
	
	    
	    
}
