package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "username")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", unique = true)
    private Team team;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // this는 Member
    }
}
