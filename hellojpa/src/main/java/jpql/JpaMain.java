package jpql;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    // 프로젝트
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("memeber1");
            em.persist(member);

            em.flush();
            em.clear();

            // 여기서 나온 모든 리스트가 영속성에서 관리
            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

            // 바뀌면 영속성에 관리
            Member findMember = result.get(0);
            findMember.setAge(20);

            // 이 방법보단
            em.createQuery("select m.team from Member m", Team.class).getResultList();
            // 아래 방법으로 해야 조인이라고 인식할 수 있음 => 예측할 수 있음
            em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
            // 임베디드 타입 프로젝션
            em.createQuery("select o.address from Order o", Address.class).getResultList();
            // 스칼라 타입
            em.createQuery("select distinct m.username, m.age from Member m").getResultList();

            List resultList = em.createQuery("select distinct m.username, m.age from Member m").getResultList();

            // Object로 조회
            Object o = resultList.get(0);
            Object[] resultObject = (Object[]) o;
            System.out.println("username = " + resultObject[0]);
            System.out.println("age = " + resultObject[1]);

            // Object[]로 조회
            List<Object[]> resultList2 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            Object[] resultObject2 = resultList2.get(0);
            System.out.println("username = " + resultObject2[0]);
            System.out.println("age = " + resultObject2[1]);

            // 제일 깔끔한거는 DTO, 나중에 QueryDSL로 더 짧게 가능
            List<MemberDTO> resultList3 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = resultList3.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    // 기본문법과 쿼리 API
    /*public static void main(String[] args) {
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
    }*/
}
