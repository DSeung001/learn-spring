package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    // 생성자 주입으로 Autowired 할 수 있지만, 생성자가 하나면 생략이 가능하다
    // 근데 또 RequiredArgsConstructor를 사용함으로써 final 붙은 값에 대한 생성자 생략이 가능
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addFrom(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item",item);
        return "basic/item";
    }

    // @ModelAttribute는 자동 Setter 호출과 모델에 매핑도 해줌!
//    @PostMapping("/add")
    public String saveItemV2(
            @ModelAttribute("item") Item item,
            Model model
    ){
        itemRepository.save(item);
//        model.addAttribute("item",item); 자동으로 추가 해준다
        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveItemV2(
            @ModelAttribute Item item
    ){
        // ModelAttribute에 alias를 지정안하면 클래스의 첫글자를 바꾼 값으로 모델에 넣어줌
        itemRepository.save(item);
        return "basic/item";
    }

    // String이나 int 같은 단순 타입이 오면 RequestParam이 적용되고 아닌 경우 객체를 통해 받음
    // 아래 처럼 ModelAttribute도 생략이 가능, 마찬가지로 모델에는 클래스 명의 첫글자가 소문자로 들어감
    @PostMapping("/add")
    public String saveItemV3(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    // 테스트 용
    // 객체가 생성된 후 별도의 초기화 작업으로 이 메소드를 진행해줌
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
