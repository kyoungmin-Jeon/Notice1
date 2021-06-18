package com.example.notice;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 생성자
@ToString
public class NoticeFacts {

		private long seq; //Required
	    
	    private String ttl; //Required
	    
	    private String cont; //Required
	    
	    private String rgstrNm; //Required
	    
	    private int readCnt; //Required
	    
	    private String notiYmd; //Required
	    
	    private String file;//optional
	    
	    private MultipartFile uploadFile;//optional
	    
	    private int prevNum; //Required

	    private int nextNum; //Required
	    
	    public static class Builder {
	    	//Required Parameter
	    	private final long seq;
	    	private final String ttl;
	    	private final String cont;
	    	private final String rgstrNm;
	    	private final int readCnt;
	    	private final String notiYmd; 
	    	private final int prevNum;
	    	private final int nextNum; 
	    	
	    	//Optional Parameter
	    	private String file = "";
	    	private MultipartFile uploadFile;
	    	
	    	public Builder(long seq, String ttl, String cont, String rgstrNm, int readCnt, String notiYmd, int prevNum, int nextNum) {
		    	this.seq = seq;
		    	this.ttl = ttl;
		    	this.cont = cont;
		    	this.rgstrNm = rgstrNm;
		    	this.readCnt = readCnt;
		    	this.notiYmd = notiYmd;
		    	this.prevNum = prevNum;
		    	this.nextNum = nextNum;
	    	}
	    	
	    	public Builder file(String val) {
	    		file = val;
	    		return this;
	    	}
	    	
	    	public Builder uploadFile(MultipartFile val) {
	    		uploadFile = val;
	    		return this;
	    	}
	    	
	    	public NoticeFacts build() {
	    		return new NoticeFacts(this);
	    	}
	    }
	    
	    private NoticeFacts(Builder builder) {
	    	seq = builder.seq;
	    	ttl = builder.ttl;
	    	cont = builder.cont;
	    	rgstrNm = builder.rgstrNm;
	    	readCnt = builder.readCnt;
	    	notiYmd = builder.notiYmd;
	    	prevNum = builder.prevNum;
	    	nextNum = builder.nextNum;
	    }
}
