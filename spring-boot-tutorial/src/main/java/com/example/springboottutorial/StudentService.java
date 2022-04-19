package com.example.springboottutorial;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> repository = Arrays.stream(
                    new Student[]{
                            new Student(1L, "Alan", "Turing"),
                            new Student(2L, "Sebastian", "Bach"),
                            new Student(3L, "Pablo", "Picasso"),
                    })
            .collect(Collectors.toConcurrentMap(Student::getId, Function.identity()));

    private final AtomicLong sequence = new AtomicLong(3);

    public List<Student> readAll() {
        return repository.values().stream().collect(Collectors.toList());
    }

    public Student read(Long id) {
        return repository.get(id);
    }

    public Student create(Student student) {
        Long key = sequence.incrementAndGet();
        student.setId(key);
        repository.put(key, student);
        return student;
    }
    public Student update(Long id, Student student) {
        student.setId(id);
        Student oldStudent = repository.replace(id, student);
        return oldStudent == null ? null : student;
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}
