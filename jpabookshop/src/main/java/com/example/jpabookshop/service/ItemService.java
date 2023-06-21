package com.example.jpabookshop.service;

import com.example.jpabookshop.domain.item.Item;
import com.example.jpabookshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    /*
    * 강사님도 나랑 마찬가지로 서비스 로직의 추가 코드가 없을 경우 바로 리포지터리 사용할지에 대한
    * 고려는 해봐야한다고 하심
    * */
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
