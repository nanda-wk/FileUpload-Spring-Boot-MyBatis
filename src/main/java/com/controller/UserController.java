package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserRepository;
import com.dto.UserRequestDTO;
import com.model.UserBean;

@Controller
public class UserController {

	@Autowired
	private UserRepository ur;

	public static String uploadDir = "/src/main/resources/static/image";
	public static String sysDir = System.getProperty("user.dir") + uploadDir ;

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index", "bean", new UserBean());
	}

	@PostMapping("/upload")
	public String upload(@ModelAttribute("bean") UserBean bean, ModelMap model) {
		UserRequestDTO dto = new UserRequestDTO();
		MultipartFile file = bean.getImg();
		StringBuilder fileNames = new StringBuilder();
		String filename = bean.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
		Path fileNameAndPath = Paths.get(sysDir, filename);

		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setImg(uploadDir);
		int i = ur.insertUser(dto);
		if (i > 0) {
			model.addAttribute("success", "Upload success!");
			return "success";
		} else {
			model.addAttribute("error", "Upload fail!");
			return "redirect:/";
		}
	}
}
