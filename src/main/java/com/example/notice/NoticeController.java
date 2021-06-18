package com.example.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	private final NoticeServiceImpl noticeService;

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@ModelAttribute("notice") Notice notice, Model model) {
		model.addAttribute("notice", new Notice());

		return "notice/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid Notice notice, Model model, BindingResult result) throws IOException {

		String fileName = null;
		MultipartFile uploadFile = notice.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			
			String ext = FilenameUtils.getExtension(originalFileName);
			UUID uuid = UUID.randomUUID();
			fileName = uuid + "." + ext;
			uploadFile.transferTo(new File("C:\\Users\\Jeon\\Downloads\\filesys\\filedata" + fileName));
			
		}
		notice.setFile(fileName);
		noticeService.insert(notice);
			
		return "redirect:/home";
	}

	@RequestMapping(value = "/file_download")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		String file = request.getParameter("file");
		String realFile = "";

		try {
			// 사용자 브라우저 알아내기
			String browser = request.getHeader("User-Agent");
			// 파일 인코딩
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				file = URLEncoder.encode(file, "UTF-8").replaceAll("\\+", "%20");
			} else {
				file = new String(file.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			log.info("UnsupportedEncodingException");
		}
		realFile = "C:\\Users\\Jeon\\Downloads\\filesys\\filedata" + file;
		File file1 = new File(realFile);
		if (!file1.exists()) {
			return;
		}

		// 파일명 지정
		response.setContentType("aplication/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFile);

			int ncount = 0;
			byte[] bytes = new byte[512];

			while ((ncount = fis.read(bytes)) != -1) {
				os.write(bytes, 0, ncount);
			}
			fis.close();
			os.close();
		} catch (Exception e) {
		}
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("page") int page, Model model, Notice notice) {
		model.addAttribute("read", noticeService.select(notice.getSeq()));
		model.addAttribute("page", page);

		return "notice/read";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("page") int page, Notice notice, Model model) {
		model.addAttribute("update", noticeService.select(notice.getSeq()));
		model.addAttribute("page", page);

		return "notice/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Notice notice) {
		noticeService.update(notice);

		return "redirect:/home";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Notice notice) {
		noticeService.delete(notice.getSeq());

		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(@RequestParam(defaultValue = "1") int page, Model model) {
		PageRequest pageRequest = new PageRequest(page, 10, "ASC", "noti_seq");
		Page<Notice> pagedNotice = noticeService.pagedNotice(pageRequest);

		model.addAttribute("page", pagedNotice);
		return "notice/home";
	}

	@GetMapping("/search")
	public String home(@RequestParam(defaultValue = "1") int page, @RequestParam(name = "type") String type,
			@RequestParam(name = "searchValue") String searchValue, Model model) {
		PageRequest pageRequest = new PageRequest(page, 10, "DESC", "noti_seq");
		Page<Notice> searchedPage = noticeService.searchedPage(pageRequest, type, searchValue);

		model.addAttribute("page", searchedPage);
		if (type.equals("제목+내용"))
			model.addAttribute("type", "제목%2B내용");
		else
			model.addAttribute("type", type);
		model.addAttribute("searchValue", searchValue);
		return "notice/search";
	}
}
