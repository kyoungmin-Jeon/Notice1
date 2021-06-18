package com.example.notice;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 생성자
@AllArgsConstructor //모든 필드가 있는 생성자
@ToString
public class Notice {
	
    private long seq;
    
    @NotBlank(message = "제목을 작성하여야 합니다.")
    @Length(min = 1, max = 20, message = "제목은 1~20자 이내여야 합니다.")
    private String ttl;
    
    @NotBlank(message = "내용을 작성하여야 합니다.")
    @Length(min = 1, max = 100, message = "내용은 1~100자 이내여야 합니다.")
    private String cont;
    
    @NotBlank(message = "작성자를 입력하여야 합니다.")
    private String rgstrNm;
    
    private int readCnt;
    
    private String notiYmd;
    
    private String file;
    
    private MultipartFile uploadFile;
    
    private int prevNum;

    private int nextNum;
    
    
    public Notice(String ttl, String cont, String rgstrNm){
        this.ttl = ttl;
        this.cont = cont;
        this.rgstrNm = rgstrNm;
    }
}
