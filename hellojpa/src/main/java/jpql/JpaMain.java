package jpql;

import javax.persistence.*;

public class JpaMain {
    // 기본문법과 쿼리 API
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Member member = new Member();
//            member.setUsername("memeber1");
//            em.persist(member);
//
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10", Member.class);
//            TypedQuery<String > query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            Member singleResult = query.getSingleResult();
//            System.out.println("singleResult = " + singleResult);

            // 결과가 없으면 NonUniqueResultException이 나오는데 이부분은 개발자들끼리 논란이 있음
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            Member result = query.getSingleResult();
//            System.out.println("result = " + result);

            // 파라미터 1
            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query2.setParameter("username", "member1");
            Member singleResult = query2.getSingleResult();
            System.out.println("singleResult = " + singleResult);

            // 아래처럼 쓸 수도 있음, 파라미터의 이름(key)기반 말고도 index 로도 가능한대 유지보스에 안좋으므로 안씀
            em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                            .getResultList();

            em.flush();
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
