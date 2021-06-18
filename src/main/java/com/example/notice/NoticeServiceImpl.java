package com.example.notice;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl {
    
	private final NoticeMapper noticeMapper;

	/**
	 *  <p>공지사항 글을 작성합니다</p>
	 *
	 * @author 전경민
	 * @param notice
	 * @throws IOException 
	 * @throws  
	 */
    public void insert(Notice notice) throws IOException {
        String file = null;
    	MultipartFile uploadFile = notice.getUploadFile();
    	if (!uploadFile.isEmpty()) {
    		//파일 원본 이름 구하기
    		String originalFileName = uploadFile.getOriginalFilename();
    		//확장자 구하기
    		String ext = FilenameUtils.getExtension(originalFileName);
    		//UUID 구하기 (고유성이 보장되는 id)
    		UUID uuid = UUID.randomUUID();
    		file = uuid + "." + ext;
    		uploadFile.transferTo(new File("C:\\Users\\Jeon\\Downloads\\filesys\\filedata" + file));
    	}
    	notice.setFile(file);
    	noticeMapper.insert(notice);
     
    }
    
    /**
     * <p>공지사항 글을 조회합니다</p>
     * 
     * @author 전경민
     * @param seq
     * @return
     */
    public Notice select(long seq) {
   
    	Notice notice = noticeMapper.select(seq);
    	
    	noticeMapper.updateHitByIdx(notice);
    	notice = noticeMapper.getData(notice);
    	
    	return notice;
    }

    /**
     * <p>공지사항 글을 수정합니다</p>
     * 
     * @author 전경민
     * @param notice
     * @return
     */
    public int update(Notice notice) {
        int update = noticeMapper.update(notice);
       
        return update;
    }

    /**
     * <p>공지사항 글을 삭제합니다</p>
     * 
     * @author 전경민
     * @param seq
     * @return
     */
    public int delete(long seq) {
    	int result = 0;
    	result = noticeMapper.delete(seq);
    	
    	return result;
    }

    public int total() {
        int total = noticeMapper.total();
        return total;
    }

    public int searchTotal(String type, String searchValue){
        int total = noticeMapper.searchTotal(type, searchValue);
        return total;
    }
    
    public Map<String, String> validateHandling(Errors errors) {
    	Map<String, String> validatorResult = new HashMap<>();
    	
    	for (FieldError error : errors.getFieldErrors()) {
    		String validKeyName = String.format("valid_%s", error.getField());
    		validatorResult.put(validKeyName, error.getDefaultMessage());
    	}
    	
    	return validatorResult;
    }

    public Page<Notice> pagedNotice(PageRequest pageRequest){
        List<Notice> notices = noticeMapper.pagedNotice(pageRequest);
        Page<Notice> page = new Page<>(pageRequest, total(), notices);
        log.info("page : "+page.toString());
        return page;
    }

    public Page<Notice> searchedPage(PageRequest pageRequest, String type, String searchValue){
        List<Notice> notices = noticeMapper.searchedNotice(pageRequest, type, searchValue);
        log.info(type);
        Page<Notice> page = new Page<>(pageRequest, searchTotal(type, searchValue), notices);

        return page;
    }
}
