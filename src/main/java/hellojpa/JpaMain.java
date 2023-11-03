package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            Team teamB = new Team();
            team.setName("TeamB");
            em.persist(teamB);

            // 회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam(); // 참조를 사용해서 연관관계 조회
            System.out.println("findTeam = " + findTeam.getName());

            // 수정
            Team newTeam = em.find(Team.class, 1L);
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
