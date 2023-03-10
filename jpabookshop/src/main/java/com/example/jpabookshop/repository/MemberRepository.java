package com.example.jpabookshop.repository;

import com.example.jpabookshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// Repository => Component 가 안에 있어 스프링에서 스캔해감
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // 자동 주입
    private final EntityManager em;

    /*public MemberRepository(EntityManager em) {
        this.em = em;
    }*/

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
