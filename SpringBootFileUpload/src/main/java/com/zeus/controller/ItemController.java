package com.zeus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.domain.Item;
import com.zeus.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
@MapperScan(basePackages = "com.zeus.mapper")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Value("${upload.path}")
	private String uploadPath;

	// 이미지 게시판 리스트 요청 /WEB-INF/views/item/list.jsp
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		List<Item> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
	}

	// 이미지 게시판 등록 화면 요청 /WEB-INF/views/item/register.jsp
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute(new Item());

		return "item/register";
	}

	// 이미지 게시판 등록 내용 db 저장 및 파일 저장 요청 /WEB-INF/views/item/success.jsp
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		log.info("originalName: " + file.getOriginalFilename());
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());
		// uploadFile(String originalName, byte[] fileData) 구조임
		// file.getBytes() => byte[] => 이미지 파일을 byte[] 로 만들어준다 .
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		this.itemService.regist(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");

		return "item/success";
	}

	// 이미지 게시판 파일 수정 요청 화면 /WEB-INF/views/item/modify.jsp
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);

		model.addAttribute(item);

		return "item/modify";
	}

	// 이미지 게시판 파일 수정 내용 db 저장 및 파일 저장 요청 /WEB-INF/views/item/success.jsp
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		if (file != null && file.getSize() > 0) {
			log.info("originalName: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
			log.info("contentType: " + file.getContentType());

			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());

			item.setPictureUrl(createdFileName);
		}
		this.itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}

	// 이미지 게시판 제거 화면 요청 /WEB-INF/views/item/remove.jsp
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);
		model.addAttribute(item);
		return "item/remove";
	}

	// 이미지 게시판 제거 내용 처리 요청(디비 및 파일 제거 요청) /WEB-INF/views/item/success.jsp
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(Item item, Model model) throws Exception {
	    try {
	        Item dbItem = this.itemService.read(item.getItemId());
	        if (dbItem == null || dbItem.getPictureUrl() == null) {
	            log.info("삭제 실패:", item.getItemId());	         
	            return "redirect:/item/list"; 
	        }

	        String path = "C:\\upload\\" + dbItem.getPictureUrl();
	        File file = new File(path);
	        if (file.exists() && file.delete()) {
	            log.info("파일 삭제 완료");
	        } else {
	            log.info("파일 삭제 실패");
	        }

	        // DB에서 Item 삭제
	        this.itemService.remove(item.getItemId());
	        return "item/success"; 

	    } catch (Exception e) {
	        log.error("파일 삭제 오류");
	        return "redirect:/item/list";
	    }
	}


	// 멤버함수: 파일명을 주면 => '중복이 일어나지 않는 이름_이미지 파일.확장자' 만들고 => c://upload 에 저장하는 함수 
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		// 절대 중복이 일어나지 않는 아이디를 생성한다 0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png
		UUID uid = UUID.randomUUID();
		// 0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png
		String createdFileName = uid.toString() + "_" + originalName;
		// File target = c:/upload.0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png
		File target = new File(uploadPath, createdFileName);
		// 진짜 데이터를 c:/upload.0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png 복사처리한다 byte[]
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}

	// 브라우저에서 이미지를 <img src="/item/disaply/(파일명(~.png) 혹은 이미지 번호(2))" />
	// 요청하면 2번 이미지 게시판에서 불러서 화면에 전달해주는 함수 
	@ResponseBody
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(Integer itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String fileName = itemService.getPicture(itemId);
		log.info("FILE NAME: " + fileName);
		try {
			// 0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png => "png"
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			// "png" => MediaType.IMAGE.PNG
			MediaType mType = getMediaType(formatName);
			// 헤더 생성 
			HttpHeaders headers = new HttpHeaders();
			// c:/upload/0170e40e-310f-40ef-95d4-eaf34445f499_sssss.png
			in = new FileInputStream(uploadPath + File.separator + fileName);

			if (mType != null) {
				// MediaType.IMAGE.PNG 헤더 추가 
				headers.setContentType(mType);
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	// 이미지 게시판 등록 화면 요청 /WEB-INF/views/item/register.jsp
	private MediaType getMediaType(String formatName) {
		if (formatName != null) {
			if (formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}

			if (formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}

			if (formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}

		return null;
	}
}
