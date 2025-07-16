package com.imchobo.demo2.controller;

import com.imchobo.demo2.domain.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
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
    f1.forEach(f -> log.info(f.getOriginalFilename()));

    List<Attach> attachs = new ArrayList<>();

    int odr = 0;
    for (MultipartFile part : f1) {
      if (part.getSize() == 0) {
        continue;
      }

      Long fileSize = part.getSize();
      String origin = part.getOriginalFilename();
      String contentType = part.getContentType();

      // image/png, image/jpg, image/gif, image/webp, image/bmp, image/jpeg

      // ext 추출
      int idx = origin.lastIndexOf("."); // 못찾으면 -1
      String ext = "";
      if (idx >= 0) {
        //확장자가 존재하는 경우
        ext = origin.substring(idx);
      }

      UUID uuid = UUID.randomUUID();
      String fileName = uuid + ext;

      boolean image = contentType.startsWith("image");
      String path = genPath();
      String realPath = UPLOAD_PATH + "/" + path + "/"; // 루트 디렉토리부터 다 결합하는거
      File file = new File(realPath);
      if (!file.exists()) {
        file.mkdirs();
      }
      part.transferTo(new File(realPath + fileName));  //원본을 저장시켜라

      if (image) {
        try {

          Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(realPath + "t_" + fileName);
        } catch (Exception e) {
          image = false;
        }
      }
      log.info("{} :: {} :: {} :: {}", fileSize, origin, contentType, ext);  // 디렉토리 하나에 너무 많이 넣으면안되서 날짜별로 나눠야하고, 원본, 썸네일처럼 줄인것도 만들어야함. 근데 일반 사진은안됨. 이미지만 그래서 mine타입을 써야함

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

  @GetMapping("display")
  private ResponseEntity<?> display(Attach attach) throws IOException {
    // 물리적 위치에 있는 실제 파일을 origin의 네임으로 치환후 다운로드
    File file = new File(UPLOAD_PATH + "/" + attach.getPath(), attach.getUuid());
    if (!file.exists()) {
      return ResponseEntity.notFound().build();

    }
    // 응답 헤더 설정

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", Files.probeContentType(file.toPath()));
    headers.add("Content-Length", String.valueOf(file.length()));

    Resource resource = new FileSystemResource(file);
    return ResponseEntity.ok().headers(headers).body(resource);

  }
  @GetMapping("download")
  private ResponseEntity<?> download(Attach attach) throws IOException {
      // 물리적 위치에 있는 실제 파일을 origin의 네임으로 치환후 다운로드
      File file = new File(UPLOAD_PATH + "/" + attach.getPath(), attach.getUuid());
      if (!file.exists()) {
        // 이미지 없을시
        return ResponseEntity.notFound().build();

      }
      // 응답 헤더 설정

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentLength(file.length());
      headers.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(attach.getOrigin(),"utf-8").replaceAll("\\+", "%20") + "\"").build());

      Resource resource = new FileSystemResource(file);
      return ResponseEntity.ok().headers(headers).body(resource);
      //pbl 에서 스트림으로 변경하기 전 코드
//      headers.add("Content-Disposition", "attach" URLEncoder.encode(attach.getOrigin(),"utf-8").replaceAll("\\+", "%20") + "\"");
//      headers.add("Content-Type", "application/octet-stream");
//      headers.add("Content-Length", String.valueOf(file.length()));

    }

}




