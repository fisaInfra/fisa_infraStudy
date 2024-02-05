package com.fisa.infra.upload;

import com.fisa.infra.board.domain.dto.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileStore {

    @Value("{spring.servlet.multipart.location}")
    private String fileDir;

    public String getPath(String filename){
        return fileDir + filename;
    }

    public UploadFile storeOneFile(MultipartFile multipartFile, String folderName) {
        String fileName = createStoreFileName(multipartFile.getOriginalFilename());
        String path = getPath(fileName);
        UploadFile profileDto =  new UploadFile(path, fileName);
        return profileDto;
    }

    public List<UploadFile> storeAllFile(List<MultipartFile> multipartFile) throws IOException {
        List<UploadFile> profileDtoList = new ArrayList<>();
        for (int i = 0; i < multipartFile.size(); i++) {
            String fileName = createStoreFileName(multipartFile.get(i).getOriginalFilename());
            String path = getPath(fileName);
            multipartFile.get(i).transferTo(new File(path));
            UploadFile uploadFile =  new UploadFile(path, fileName);
            profileDtoList.add(uploadFile);
        }
        return profileDtoList;
    }

    private String createStoreFileName(String originalFilename) {
        // 서버에 저장하는 파일 명
        String uuid = UUID.randomUUID().toString();
        // 사진 포맷 추출
        String ext = extractExt(originalFilename);
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
