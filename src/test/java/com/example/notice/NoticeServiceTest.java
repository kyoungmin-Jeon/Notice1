package com.example.notice;

import lombok.extern.slf4j.Slf4j;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(value = false)
class NoticeServiceTest {

    @Autowired
    private NoticeServiceImpl noticeService;

    @Test
    void save() {
        for (int i = 0; i < 34; i++) {
            Notice notice = new Notice("ttl", "cont unique", "rgstrNm");
//            noticeService.insert(notice);
        }
    }

//    @Test
//    void read() {
//        Notice notice = noticeService.select(2l);
//        log.info(notice.toString());
//    }
//
//    @Test
//    void update() {
//        Notice notice = noticeService.select(1l);
//        notice.setTtl("ttl changed");
//        notice.setCont("cont changed");
//        noticeService.update(notice);
//    }

    @Test
    void delete() {
        noticeService.delete(155l);
    }

    @Test
    void total() {
        int total = noticeService.total();
        log.info("total : "+total);
    }

    @Test
    void pagedNotice() {
        PageRequest pageRequest = new PageRequest(1,10,"DESC", "noti_seq");
        Page<Notice> noticePage = noticeService.pagedNotice(pageRequest);
        log.info(noticePage.toString());
        List<Notice> results = noticePage.getResults();
        for (Notice result : results) {
            log.info(result.toString());
        }
    }

    @Test
    void searchedPage() {
        PageRequest pageRequest = new PageRequest(1,10,"DESC", "noti_seq");
        Page<Notice> searchedPage = noticeService.searchedPage(pageRequest, "제목", "unique");
        List<Notice> results = searchedPage.getResults();
        for (Notice result : results) {
            log.info("results : "+result);
        }
    }
}