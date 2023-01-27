package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속 상태, EntityManager 안에 영속성 컨텍스트에서 관리됨 => 바로 쿼리가 실행되지 않음
            // BEFORE & AFTER 후에 쿼리가 로그에 찍힘
            System.out.println("=========BEFORE=========");
            em.persist(member);
//            em.detach(member); // 영속성 컨텍스트에서 분리 => 준영속 상태 => 쿼리 실행을 안함
            System.out.println("=========AFTER=========");

            // insert 한 값이 1차 캐시에 저장되서 별도의 select 쿼리가 실행안함
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            // Transaction을 commit시 쿼리 실행
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

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
