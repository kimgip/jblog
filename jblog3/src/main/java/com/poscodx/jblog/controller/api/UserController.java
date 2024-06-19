package com.poscodx.jblog.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscodx.jblog.dto.JsonResult;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseBody
	@GetMapping("/checkid")
	public JsonResult checkId(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo vo = userService.getUser(id);
		return JsonResult.success(vo != null);
	}

}
