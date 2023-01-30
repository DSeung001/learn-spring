package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    // 단원 : 엔티티 매핑
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            // 아래 예시는 연관관계를 쓰지 않고 객체 중심이 아닌 테이블 중심의 설계를 햇을 경우 어떻게 개발해야지에 대한 것
//            Team team = new Team();
//            team.setName("TeamA");;
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            // 연관관계 사용 후
            Team team = new Team();
            team.setName("TeamA");;
            em.persist(team);

            System.out.println("====1");
            em.flush();
            em.clear();
            System.out.println("====2");

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    // 단원 : 영속성 관리 - 내부 동작 방식
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속성 : 준영속 상태로 만들어서 영속성을 쓰지 못해 쿼리가 2번 실행됨!
            Member member = em.find(Member.class, 200L);
            member.setName("bbbb");

            em.clear();

            Member member2 = em.find(Member.class, 200L);

            // 영속 - Flush

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush();
//            System.out.println("=========");


            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

    /*
    단원 : JPA 소개
    public static void main(String[] args) {
        // EntityManagerFactory는 WAS에 올라오는 시점에 하나만 생겨서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager는 한번 쓰고 버리는 것, 쓰레드간에 공유하면 안됨 (DB 커넥션)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
//            Insert
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

//            Delete
//            em.remove(findMember);

//            아래 Setter 메서드로 데이터를 업데이트할 수 있음, 신기하게도 업데이트하는 문장을 넣지 않아도 업데이트 됨 => JPA를 통해(em) 가져온 값을 setter 하면 값을 commit 하기 전에 업데이트 해버림
//            findMember.setName("HelloJPA");

            // Query가 다른데, 이유는 테이블 대상이 아닌 객체를 대상으로
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            // Entity Manager가 DB 커넥션을 물고있음
            em.close();
        }
        emf.close();
    }*/
}
