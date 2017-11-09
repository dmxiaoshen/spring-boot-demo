package com.dmxiaoshen.controller;

import com.dmxiaoshen.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by hzhsg on 2017/11/9.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    static Map<String,Book> bookcase = Collections.synchronizedMap(new HashMap<>());

    @GetMapping("/")
    public List<Book> getBookList(){
        return new ArrayList<>(bookcase.values());
    }

    @PostMapping("/")
    public String postBook(@ModelAttribute("book")Book book){
        // 同意是把参数绑定成对象
        // @ModelAttribute 可直接接收url?后面的参数 绑定成pojo
        // @RequestBody 接收json串，如ajax请求数据 绑定成pojo
        bookcase.put(book.getId(),book);
        return "success";
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id")String id){
        return bookcase.get(id);
    }

    @PutMapping("/{id}")
    public String putBook(@PathVariable("id")String id,@RequestBody Book book){
        bookcase.put(id,book);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")String id){
        bookcase.remove(id);
        return "success";
    }
}
