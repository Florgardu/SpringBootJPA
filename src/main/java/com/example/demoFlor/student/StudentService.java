package com.example.demoFlor.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return this.studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException("email taken");}
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
      boolean exist= studentRepository.existsById(id);
      if (!exist){
          throw new IllegalStateException("student with id " +
                  id+ "does not exists");
      }
      studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
      Student s= studentRepository.findById(id)
              .orElseThrow( () -> new IllegalStateException(
                      "student with id" + id + "does not exist"
                      )
              );
      if (email != null && email.length()> 0){
          s.setEmail(email);
      }

      if (name != null  && name.length()> 0){
          s.setName(name);
      }
    }
}
