package com.example.school.repositories.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.school.database.entities.Student;
import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.exceptions.UserNotFoundInDatabase;
import com.example.school.repositories.StudentRepository;
import com.example.school.repositories.TeacherRepository;
import com.example.school.repositories.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository<User>{
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private TeacherRepository teacherRepo;

	@Override
	public <S extends User> S save(S entity) {
		if (entity.isStudent()) {
			Student student = studentRepo.save((Student) entity);
			return (S) student;
		} else {
			Teacher teacher = teacherRepo.save((Teacher) entity);
			return (S) teacher;
		}
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByEmail(String email) {
		System.out.println("searching for user....");
		Optional<Student> result = studentRepo.findById((long) 1);
		if (result.isPresent()) {
			Student stud = result.get();
			return stud;
		} else {
			throw new UserNotFoundInDatabase("No such user in database with the email " + email);
		}
	}

	
}
