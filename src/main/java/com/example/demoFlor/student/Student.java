package com.example.demoFlor.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name="Student")
@Table(
       name = "student",
        uniqueConstraints = {
               @UniqueConstraint( name = "email_unique", columnNames = "email")
        }
)
public class Student {
    @Id
    @SequenceGenerator(
            name ="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1 // en cuanto queremos que se incremente
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="name",
            nullable = false ,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name="lastName",
            nullable = false ,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name="email",
            nullable = false ,
            columnDefinition = "TEXT"
    )
    private String email;

    private LocalDate dob;
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(String name,String lastName, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.lastName= lastName;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
