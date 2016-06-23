package jpamodule;

import javax.persistence.*;

/**
 * Created by hdhamee on 6/23/16.
 */

@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String studentName;

    public String getUsername() {
        return studentName;
    }

    public void setUsername(String username) {
        this.studentName = username;
    }

    public Long getId() {
        return id;
    }
}
