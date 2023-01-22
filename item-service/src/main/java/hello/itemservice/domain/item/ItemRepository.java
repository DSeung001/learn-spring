package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 컴포넌트 스캔의 대상
// Spring Container 안에 사용하면 싱글톤이지만 따로 쓸 경우 static 이 필수임
// 지금 상태는 static 을 안써도 되는 상황

@Repository
public class ItemRepository {

    // 실제는 HashMap 사용 x
    // 실무에서는 멀티쓰레드 환경에서 여러대가 Store 접근하면 HashMap쓰면 안되고
    // 사용하려면 ConcurrentMap<>()을 써야 함
    private static final Map<Long, Item> store = new HashMap<>(); // static
    // 애도 동시 접근하면 꼬이니 AtomicLong 을 써야함
    private static long sequence= 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    // 그대로 반환해도 되지만 list 로 감싸서 반환하는 이유는
    // list의 값을 바꿔도 원본에 영향을 주지 못하기 때문
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    // 정석으로 하려면 DTO 선언하는게 맞음
    // 항상 중복 vs 명확성이면 명확성을 우선시
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void cleanStore(){
        store.clear();
    }
}
