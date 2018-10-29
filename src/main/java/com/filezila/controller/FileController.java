package com.filezila.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.filezila.model.FileDetails;
import com.filezila.model.User;
import com.filezila.service.AmazonClientService;
import com.filezila.service.FileService;

@Controller
// @RequestMapping("/storage")
public class FileController {

	@Autowired
	private AmazonClientService amazonClientService;

	@Autowired
	private FileService fileService;

	@Autowired
	FileController(AmazonClientService amazonClientService) {
		this.amazonClientService = amazonClientService;
	}


	@PostMapping("/uploadSampleFile")
	public String uploadSampleFile(@RequestPart(value = "file") MultipartFile file,@RequestParam(value="description",required=false) String description,Model model,HttpSession session) {
		FileDetails  details=new FileDetails();
		Timestamp updatedTime=new Timestamp(new Date().getTime());
		details.setCreatedTime(updatedTime);
		details.setFileDescription(description);
		details.setFileName(file.getOriginalFilename());
		
		details.setFileSize(file.getSize());
		details.setUpdatedTime(updatedTime);
		User loggedUser=(User)session.getAttribute("loggedUser");
		details.setEmail(loggedUser.getEmail());
		
		String userName = loggedUser.getFirstName();
		
		String  path=this.amazonClientService.uploadSampleFile(file, userName);
		details.setFilePath(path);
		fileService.addFileDetails(details);
		/*String role=loggedUser.getRole();
		List<FileDetails> fileDetails=new ArrayList<>();
		if(!"admin".equalsIgnoreCase(role)){
		   fileDetails=fileService.findAllFileDetailsByEmail(loggedUser.getEmail());
		}else {
			fileDetails=fileService.findAllFileDetails();
		}
		model.addAttribute("fileDetails", fileDetails);*/
		return "redirect:/home";
	}

	
	
	
	
	
	 @GetMapping("/deleteFile")
	public String deleteFile(@RequestParam(value = "fileName") String fileName,
			@RequestParam(value = "fileid") int fileid,HttpSession session) {
		
		 User loggedUser=(User)session.getAttribute("loggedUser");
			
			String userName = loggedUser.getFirstName();
		 
		this.amazonClientService.deleteFileFromS3Bucket(fileName, userName);
		fileService.deleteFileByFid(fileid);
		
		return "redirect:/home";
	
	}
	 
	 
	 @GetMapping("/downloadFile")
		public ResponseEntity downLoadFile(@RequestParam(value = "fileName") String fileName,
				HttpServletRequest request,HttpServletResponse response, HttpSession session) {
			
		 
			User loggedUser=(User)session.getAttribute("loggedUser");
		
			String userName = loggedUser.getFirstName();
		 	 
			byte[] data=this.amazonClientService.downloadFile(fileName, userName);
			

	        // Try to determine file's content type
	        String contentType = null;
	    
	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                .body(data);
		}
	 
	 
	 
	 
	 

}
