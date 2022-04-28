package com.example.springboottutorial;

import com.example.springboottutorial.exception.DetailErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public ListResponse<Student> getStudentList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0 || size > 10) {
            size = 10;
        }
        Page<Student> pg = studentRepository.findAll(PageRequest.of(page, size));
        List<Student> studentList = pg.getContent();

        ListResponse<Student> listResponse = new ListResponse<>();
        listResponse.setTotal(pg.getNumberOfElements());
        listResponse.setPage(page);
        listResponse.setSize(size);
        listResponse.setData(studentList);
        return listResponse;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new DetailErrorException(HttpStatus.NOT_FOUND, String.format("%d not found", id)));
    }

    @PostMapping("/")
    public Student create(@Valid @RequestBody Student student) {
        return this.studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@RequestBody Student student, @PathVariable Long id) {
        return this.studentRepository.findById(id)
                .map(stu -> {
                    stu.setFirstName(student.getFirstName());
                    stu.setLastName(student.getLastName());
                    return this.studentRepository.save(stu);
                })
                .orElseGet(() -> {
                    student.setId(id);
                    return this.studentRepository.save(student);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        this.studentRepository.deleteById(id);
    }
}
