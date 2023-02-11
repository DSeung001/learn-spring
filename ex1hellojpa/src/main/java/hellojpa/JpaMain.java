package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {
    // 단원 : 값 타입 컬랙션
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "100000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

//            member.getAddressHistory().add(new Address("old1", "street", "10000"));
//            member.getAddressHistory().add(new Address("old2", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============ START ===========");
            // 값 타입의 나머지는 지연 로딩
            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식, 애도 임뮤터블해야함 => 어떤게 값을 참조할지 모르니~
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("치킨");

            System.out.println("============ Address ===========");
            // 대부분 컬랙션을 값을 지울 때 equals로 해결함, 그래서 임베디드 값 타입의 equals를 잘 해줘야함
            // 연관돤 Address를 다 지우고 다시 값을 넣음 => 왜냐하면 값 타입은 엔티티와 다르게 식벽잘가 없기에 업데이트하는 로직이 힘들어짐
            // 이걸 해결할 수 있지만 @OrderColumn 등을 넣어서로 되지만, 로직이 복잡해짐
            // 왜케 복잡하기만 하냐 그래서 결론은 일대다 관계를 쓰는 걸 고려하는게 실무에서 좋다
//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    // 단원 : 값 타입
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Address address = new Address("city", "street","zipcode");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            // 업데이트 시 통으로 업데이트 (불변을 지켜야하니깐)
            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);

            Address copyAddress = new Address("newCity", address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

//            불변으로 바꿔서 이제 setter은 안됨
//            member.getHomeAddress().setCity("newCity");

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

    // 단원 : 프록시와 연관관계
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            // 영속성 청소
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

    // 단원 : 프록시와 연관관계 관리 - 프록시
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);
            
            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); // proxy
            refMember.getUsername();
            System.out.println("emf.getPersistenceUnitUtil().isLoaded() = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            Hibernate.initialize(refMember); // 강제 초기화


            // 아래 3개는 준영속으로 만들어서 refMember에서 에러 발생
//            em.close();
//            em.clear();
//            em.detach(refMember); // 영속성 컨텍스트에서 제외 => 준영속으로 만들어서 refMember에서 에러 발생
//
//            refMember.getUsername(); // 프록시 초기화
//
//            System.out.println("refMember.getUsername() = " + refMember.getUsername());

          *//*  // getReference, 영속성에 있다면 프록시가 아닌 실제 값을 줌
            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getClass()); // member

            // 아래 ==은 한 영속성 컨텍스트에서 가져온 것이기에 항상 true를 반환함
            System.out.println("a == a : "+(refMember == findMember));*//*


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

    /*private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1 instanceof Member));
        System.out.println("m1 == m2 : " + (m2 instanceof Member));
        // 이 경우 프록시를 쓸 수 있으므로 == 비교가 아닌 instanceof 사용을 권장
    }*/


    // 단원 : 고급 매핑
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());
            member.setUsername("user1");
            em.persist(member);

            em.flush();
            em.close();

            *//*Movie movie = new Movie();
            movie.setDirector("봉준호");
            movie.setActor("배우");
            movie.setName("기생수");
            movie.setPrice(10000);

            em.persist(movie);

            // 영속성 컨텍스트 초기화
            em.flush();
            em.clear();

//            Movie findMovie = em.find(Movie.class, movie.getId());

            Item findItem = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + findItem);
*//*
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/


    // 단원 : 다양한 연관관계 매핑
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

    // 단원 : 엔티티 매핑
    /*public static void main(String[] args) {
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
//            member.changeTeamId(team.getId());
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            // 연관관계 사용 후

            // 저장

            Team team = new Team();
            team.setName("TeamA");;
            // 주인이 아닌 방향, 역방향으로 할 경우 들어가지 않음
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            // changeTeam과 하나를 선택 => 무한루프 방지
//            team.addMember(member);

//            team.getMembers().add(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시을 가져오면 jpa가 동작을 안함!
            // => 작업전 FLUSH 필요
            List<Member> members = findTeam.getMembers();

            System.out.println("======");
            for (Member m : members) {
                System.out.println("member.getUsername() = " + m.getUsername());
            }
            System.out.println("======");
            *//*
            * 양방향에서는 양쪽에 값을 설정하자
            * 개발에는 단방향으로 작업하다가 양방향으로 변경하는 방향으로
            * 주체에서 메서드를 만들어 관리하자
            * *//*

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }*/

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
