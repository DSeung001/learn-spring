package com.example.jpabookshop.repository;

import com.example.jpabookshop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);
        } else {
            // 업데이트와 유사하지만 업데이트는 아니긴 함
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select * from Item", Item.class).getResultList();
    }
}
