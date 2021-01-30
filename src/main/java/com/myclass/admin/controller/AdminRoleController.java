package com.myclass.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.AddRoleDto;
import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;

@RestController
@RequestMapping("api/admin/role")
public class AdminRoleController {
	private RoleService roleService;

	@Value("${message.id}")
	private String idIsNotExist;

	@Value("${message.name}")
	private String nameIsExist;

	@Value("${role}")
	private String role;

	@Value("${role.rule}")
	private String roleRule;

	public AdminRoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách role
			return new ResponseEntity<Object>(roleService.getAllRole(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			// check xem role id có tồn tại hay không
			if (!roleService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về role theo id gửi lên
			return new ResponseEntity<Object>(roleService.getRoleById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("")
	public Object post(@Valid @RequestBody AddRoleDto entity) {
		try {
			// check xem role name có bị trùng hay không
			if (roleService.checkExistByName(entity.getName()))
				return new ResponseEntity<Object>(nameIsExist, HttpStatus.BAD_REQUEST);

			// check xem role name có bắt đầu bằng ROLE_ hay không
			if (!entity.getName().startsWith(role))
				return new ResponseEntity<Object>(roleRule, HttpStatus.BAD_REQUEST);

			// thêm role mới vào database
			roleService.add(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{id}")
	public Object put(@Valid @RequestBody RoleDto entity, @PathVariable int id) {
		try {
			// check xem id gửi lên và id trong role có giống nhau hay không
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

			// check xem role id có tồn tại hay không
			if (!roleService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem role name có bị trùng hay không
			if (roleService.checkExistByName(entity.getName()))
				return new ResponseEntity<Object>(nameIsExist, HttpStatus.BAD_REQUEST);

			// check xem role name có bắt đầu bằng ROLE_ hay không
			if (!entity.getName().startsWith(role))
				return new ResponseEntity<Object>(roleRule, HttpStatus.BAD_REQUEST);

			// sửa role dưới database
			roleService.edit(entity);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			// check xem role id có tồn tại hay không
			if (!roleService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// xóa role dưới database
			roleService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
