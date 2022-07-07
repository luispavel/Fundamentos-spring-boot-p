package com.fundamentosp.springboot.fundamentos.controller;

import com.fundamentosp.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosp.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosp.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosp.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosp.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserRestController {
	private GetUser getUser;
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;

	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser,
							  UpdateUser updateUser) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;
	}
	@GetMapping("/")
	List<User> get() {
		return getUser.getAll();
	}

	@PostMapping("/")
	ResponseEntity<User> newUser(@RequestBody User newUser) {
		return new ResponseEntity<>(createUser.save(newUser),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	ResponseEntity deleteUser(@PathVariable Long id) {
		deleteUser.remove(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	ResponseEntity<User> resplaceUser(@PathVariable Long id, @RequestBody User newUser) {
		return new ResponseEntity<>(updateUser.update(id, newUser), HttpStatus.OK);

	}
}
