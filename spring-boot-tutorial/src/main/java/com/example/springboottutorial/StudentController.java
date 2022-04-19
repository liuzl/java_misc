package com.example.springboottutorial;

import com.example.springboottutorial.exception.DetailErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    StudentController(StudentService studentService){
        this.service = studentService;
    }

    @GetMapping("/")
    public List<Student> read() {
        return service.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read(@PathVariable("id") Long id)  {
        Student foundStudent = service.read(id);
        if (foundStudent == null) {
            throw new DetailErrorException(HttpStatus.NOT_FOUND, String.format("%d not found", id));
        } else {
            return ResponseEntity.ok(foundStudent);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student createdStudent = service.create(student);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdStudent);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable Long id) {
        Student updatedStudent = service.update(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedStudent);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
