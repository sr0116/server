package com.imchobo.demo2.controller;

import com.imchobo.demo2.domain.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class UploadController {
  public static final String UPLOAD_PATH = "d:/upload/files";

  @GetMapping("upload")
  public String uploadForm() {
    return "uploadForm";
  }

  @PostMapping("upload")
  @ResponseBody
  public ResponseEntity<?> upload(List<MultipartFile> f1) throws IOException {  //여러개니까 list로 가져오고, multiparFile이름이 f1
    f1.forEach( f -> log.info(f.getOriginalFilename()));

    List<Attach> attachs = new ArrayList<>();

    int odr = 0;
    for(MultipartFile part : f1) {
      if(part.getSize() == 0) {
        continue;
      }

      Long fileSize = part.getSize();
      String origin =  part.getOriginalFilename();
      String contentType = part.getContentType();

      // image/png, image/jpg, image/gif, image/webp, image/bmp, image/jpeg

      // ext 추출
      int idx = origin.lastIndexOf("."); // 못찾으면 -1
      String ext = "";
      if(idx >= 0) {
        //확장자가 존재하는 경우
        ext = origin.substring(idx);
      }

      UUID uuid = UUID.randomUUID();
      String fileName = uuid + ext;

      boolean image = contentType.startsWith("image");
      String path = genPath();
      String realPath = UPLOAD_PATH + "/" + path + "/"; // 루트 디렉토리부터 다 결합하는거
      File file = new File(realPath);
      if(!file.exists()) {
        file.mkdirs();
      }
      part.transferTo(new File (realPath + fileName));  //원본을 저장시켜라

      if(image) {
        try {

          Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(realPath + "t_" + fileName);
        }
        catch(Exception e) {
          image = false;
        }
      }
      log.info("{} :: {} :: {} :: {}", fileSize, origin, contentType, ext );  // 디렉토리 하나에 너무 많이 넣으면안되서 날짜별로 나눠야하고, 원본, 썸네일처럼 줄인것도 만들어야함. 근데 일반 사진은안됨. 이미지만 그래서 mine타입을 써야함

      attachs.add(Attach.builder()
              .uuid(fileName)
              .origin(origin)
              .image(image)
              .path(path)
              .odr(odr++)
              .size(fileSize)
              .build());
    }


    return ResponseEntity.ok().body(attachs);
  }
  private String genPath() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime());
  }
}




