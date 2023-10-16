package com.nelioalves.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		try {
			User user = userRepository.findById(id).get();
			return user;
		}catch(Exception e){
			throw new ObjectNotFoundException("Error: Id não encontrado");
		}
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User uptade(User user) {
		User userPut = findById(user.getId());
		uptadeData(userPut, user);
		return userRepository.save(userPut);
	}
	
	public void uptadeData(User userPut, User user) {
		userPut.setEmail(user.getEmail());
		userPut.setName(user.getName());
	}
	
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
}
