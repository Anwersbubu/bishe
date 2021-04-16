package com.yj.bishe.demo.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yj.bishe.demo.config.CloudStorageConfig;
import com.yj.bishe.demo.dao.ListingsMapper;
import com.yj.bishe.demo.dao.UserMapper;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class QiNiuController {

	@Resource
	CloudStorageConfig qnconfig;

	//房源图片上传
	@PostMapping("/MultipleUpload")
	public JsonResult upload(@RequestParam("file") List<MultipartFile> file) throws IOException {
		JsonResult ret;
//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//		String hidstr = request.getParameter("lid");
//		int hid = Integer.valueOf(hidstr);
		if(!file.isEmpty()) {
			System.out.println("------------开始上传---------------");
		// 用来放多个图片的字符串
		Map<String, String> imgs = new HashMap<>();
		int n = 1;
		// 七牛的配置信息
		UploadManager qiniu = new UploadManager(new Configuration(Zone.zone2()));
//		System.out.println(qnconfig.getQiniuAccessKey() + "--" + qnconfig.getQiniuSecretKey());
		Auth auth = Auth.create(qnconfig.getQiniuAccessKey(), qnconfig.getQiniuSecretKey());
		String token = auth.uploadToken(qnconfig.getQiniuBucketName());
		for (MultipartFile f : file) {
			// 获取文件名 注意要确保唯一性
			// 文件名
			String fileName = new StringBuilder().append(UUID.randomUUID()).append(f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."))).toString();
			if (!f.isEmpty()) {
				FileInputStream inputStream = (FileInputStream) f.getInputStream();
				try {
					// 上传图片文件
					Response res = qiniu.put(inputStream, fileName, token, null, null);
					if (!res.isOK()) {
						throw new RuntimeException("上传七牛出错：" + res.toString());
					}
					// 解析上传成功的结果
					ObjectMapper gson = new ObjectMapper();
					DefaultPutRet putRet = gson.readValue(res.bodyString(), DefaultPutRet.class);
					String path = qnconfig.getQiniuDomain() + putRet.key;
					// 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
//					System.out.println("七牛云返回的图片链接:" + path);
					imgs.put("img" + n++, path);
				} catch (QiniuException e) {
					e.printStackTrace();
				}
			}
		}
		if (!imgs.isEmpty()){
			String json = JSONObject.toJSONString(imgs);
			ret = new JsonResult(true, "上传成功");
			ret.setData("imgs",json);
			System.out.println("-----------上传成功-----------");
		}else ret = new JsonResult(false,"文件上传失败");
		} else {
			ret = new JsonResult(false, "上传数据缺失");
		}
		return ret;
	}
}
