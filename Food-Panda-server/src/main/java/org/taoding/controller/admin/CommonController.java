package org.taoding.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.taoding.constant.MessageConstant;
import org.taoding.result.Result;
import org.taoding.utils.AliOssUtil;

import java.io.IOException;
import java.util.UUID;

/**
 * @Date 6/25/24 17:25
 * @Author Tao Ding
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Tag(name = "通用接口")
public class CommonController {
    @Resource
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @Operation(description = "文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}", file);
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名后缀

            String extension = null;
            if (originalFilename != null) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(),newFileName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
