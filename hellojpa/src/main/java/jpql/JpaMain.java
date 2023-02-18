package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    // 패치 조인 2 - 한계 ~ 엔티티 직접 사용
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            member4.setTeam(teamB);
            em.persist(member4);

            em.flush();
            em.clear();
            // 패치 조인 2
            String query = "select t from Team t";
            List<Team> resultList = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());

            for (Team team : resultList) {
                System.out.println("team.getName()+ \" | \"+team.getMembers().size() = " + team.getName()+ " | "+team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("member = " + member);
                }
            }

            // 엔티티 직접 사용
//            String query1 = "select m from Member m where m = :member";
//            Member findMember = em.createQuery(query1, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();
            String query1 = "select m from Member m where m.team = :team";

            List<Member> members1 = em.createQuery(query1, Member.class)
                    .setParameter("team", teamA)
                    .getResultList();
//            System.out.println("findMember = " + findMember);

            for (Member member : members1) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    // 패치 조인 1 - 기본
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            member4.setTeam(teamB);
            em.persist(member4);
            
            em.flush();
            em.clear();

            // 설정을 지연 로딩
            // join fatch를 함으로써 프록시를 사용하지 않고 실제 데이터를 가져오는 형태로 데이터를 바로 채움
*//*            String query = "select m from Member m join fetch m.team";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUsername() + \", \"+member.getTeam().getName() = " + member.getUsername() + ", "+member.getTeam().getName());
            }*//*

            // 패치 조인
            String query = "select distinct t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class).getResultList();

            System.out.println("resultList.size() = " + resultList.size());

            for (Team team : resultList) {
                System.out.println("team.getName()+ \" | \"+team.getMembers().size() = " + team.getName()+ " | "+team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("member = " + member);
                }
            }

            // 일반 조인
            String query2 = "select t from Team t join t.members m";
            List<Team> resultList2 = em.createQuery(query2, Team.class).getResultList();

            System.out.println("resultList.size() = " + resultList2.size());

            for (Team team : resultList2) {
                System.out.println("team.getName()+ \" | \"+team.getMembers().size() = " + team.getName()+ " | "+team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("member = " + member);
                }
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
    */

    // JPQL 타입 표현과 기타식 ~ JPQL 함수 ~ 경로 표현식
    /*
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setType(MemberType.ADMIN);
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("100");
            em.persist(member1);

            Member member2 = new Member();
            em.persist(member2);

            em.flush();
            em.clear();

            String query ="select concat('a','b'), substring(m.username, 2,3), locate('e','abcdegf') from Member m";
            List<Object[]> result = em.createQuery(query).getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[0] = " + objects[1]);
                System.out.println("objects[0] = " + objects[2]);
            }

            em.flush();
            em.clear();

            String query2 = "select group_concat(m.username) From Member m";
            List<String> result2 = em.createQuery(query2, String.class).getResultList();

            for (String s : result2) {
                System.out.println("s = " + s);
            }
            // 상태 필드
//            String query3 = "select m.username From Member m";
            // 단일 값 연관 경로
//            String query3 = "select m.team From Member m";
            // 컬랙션 값 연관 경로
//            String query3 = "select t.members From Team t";

            // 이거 써라 ~
            String query3 = "select m.username From Team t join t.members m";
            List<Collection> result3 = em.createQuery(query3, Collection.class)
                            .getResultList();

            System.out.println("result3 = " + result3);
            

//            String query1 = "select size(t.members) from Team t";
//            List<Integer> resultList = em.createQuery(query1, Integer.class)
//                    .getResultList();
//            for (Integer i : resultList) {
//                System.out.println("i = " + i);
//            }
*/
/*            // JPQL 타입
//            String query = "select m.username, 'HELLO', true from Member m " +
//                    "where m.type=jpql.MemberType.ADMIN";
            String query = "select m.username, 'HELLO', true from Member m " +
                    "where m.type=:userType and " +
                    " m.age between 0 and 100";
            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();


            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

            // 조건식 case
//            String query = "select" +
//                    " case when m.age <= 10 then '학생요금'" +
//                    " when m.age >= 60 then '경로요금'" +
//                    " else '일반요금'" +
//                    " end" +
//                    " from Member m";

//            String query = "select coalesce(m.username, '이름 없는 회원') as username from Member m";
            String query = "select nullif(m.username, 'teamA') as username from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
            }*//*

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }*/

    // 조인
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m from Member m inner join m.team t";
            // left outer join에서 outer는 생략 가능
//            String query = "select m from Member m left outer join m.team t";
//            String query = "select m from Member m, Team t where m.username = t.name";

            // On 추가
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
            // 막조인, 연관관계는 빼고도 조인 가능
            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }*/

    // 페이징 API
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            for (int i = 0; i< 100; i++){
                Member member = new Member();
                member.setUsername("memeber"+i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1).setMaxResults(10).getResultList();

            System.out.println("result.size() = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }
            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }*/

    // 프로젝트
    /*public static void main(String[] args) {
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
    }*/

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
