package org.king.controller;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectResult;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import lombok.extern.slf4j.Slf4j;
import org.king.config.OssProperties;
import org.king.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //允许上传的图片格式
    private static final List<String> ALLOWED_EXT = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp");

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private OssProperties ossProperties;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        //1. 校验文件
        if (file == null || file.isEmpty()) {
            return Result.error("上传的文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }
        if (!ALLOWED_EXT.contains(ext)) {
            return Result.error("仅支持 jpg/jpeg/png/gif/bmp/webp 图片格式");
        }

        //2. 生成唯一对象名，避免重名覆盖（UUID + 原始扩展名）
        String objectName = "images/" + UUID.randomUUID() + ext;

        //3. 上传到 OSS
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectResult result = ossClient.putObject(PutObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucket())
                    .key(objectName)
                    .body(BinaryData.fromStream(inputStream))
                    .contentLength((int) file.getSize())
                    .build());

            log.info("图片上传成功, objectName:{}, requestId:{}", objectName, result.requestId());
        }

        //4. 拼接可访问的 URL 并返回
        String url = "https://" + ossProperties.getBucket()
                + ".oss-" + ossProperties.getRegion() + ".aliyuncs.com/" + objectName;
        return Result.success(url);
    }
}
