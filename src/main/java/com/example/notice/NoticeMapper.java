package com.example.notice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
	
	/**
	 *  <p>공지사항 글을 작성합니다</p>
	 *
	 * @author 전경민
	 * @param notice
	 */
	void insert(Notice notice);
    
	/**
	 * <p>공지사항 글을 조회합니다</p>
	 *
	 * @author 전경민
	 * @param seq
	 * @return
	 */
    Notice select(long seq);
    
    /**
     * <p>공지사항 글의 조회수를 증가시킵니다</p>
     * 
     * @author 전경민
     * @param notice
     * @return
     */
    int updateHitByIdx(Notice notice);
    
    /**
     * <p>공지사항 글을 수정합니다</p>
     *
     * @author 전경민
     * @param notice
     * @return
     */
    int update(Notice notice);
     
    /**
     * <p>공지사항 글을 삭제합니다</p>
     * @param seq
     * @return
     */
    int delete(long seq);
    
    Notice getData(Notice notice);

    //default total
    int total();

    //search total
    int searchTotal(@Param("type") String type, @Param("searchValue") String searchValue);
    
    List<Notice> pagedNotice(PageRequest pageRequest);

    //search
    List<Notice> searchedNotice(@Param("pageRequest") PageRequest pageRequest,
                                @Param("type") String type, @Param("searchValue") String searchValue);
}
