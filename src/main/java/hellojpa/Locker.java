package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Locker {
    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker", fetch = FetchType.LAZY)
    private Member member;

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
}
