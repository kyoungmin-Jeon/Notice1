package com.example.notice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public interface NoticeService{
	
	
	/**
	 *  <p>공지사항 글을 작성합니다</p>
	 *
	 * @author 전경민
	 * @param notice
	 */
    public void insert(Notice notice) throws Exception;
    
    /**
     * <p>공지사항 글을 조회합니다</p>
     * 
     * @author 전경민
     * @param seq
     * @return
     */
    public Notice select(long seq);

    /**
     * <p>공지사항 글을 수정합니다</p>
     * 
     * @author 전경민
     * @param notice
     * @return
     */
    public int update(Notice notice);

    /**
     * <p>공지사항 글을 삭제합니다</p>
     * 
     * @author 전경민
     * @param seq
     * @return
     */
    public int delete(long seq);

    public int total();

    public int searchTotal(String type, String searchValue);

    public Page<Notice> pagedNotice(PageRequest pageRequest);

    public Page<Notice> searchedPage(PageRequest pageRequest, String type, String searchValue);
    
}
