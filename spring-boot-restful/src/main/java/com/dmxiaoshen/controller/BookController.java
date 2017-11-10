package com.dmxiaoshen.controller;

import com.dmxiaoshen.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by hzhsg on 2017/11/9.
 */
@RestController
@RequestMapping("/books")
@Api(value = "书籍",description = "书籍")
public class BookController {

    static Map<String,Book> bookcase = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "查询书籍列表",notes = "查询书籍列表",httpMethod = "GET",response = Book.class)
    @GetMapping("/")
    public List<Book> getBookList(){
        return new ArrayList<>(bookcase.values());
    }

    @ApiOperation(value = "新增书籍",notes = "新增书籍",httpMethod = "POST",response = String.class)
    @PostMapping("/")
    public String postBook(@ModelAttribute("book")Book book){
        // 同意是把参数绑定成对象
        // @ModelAttribute 可直接接收url?后面的参数 绑定成pojo
        // @RequestBody 接收json串，如ajax请求数据 绑定成pojo
        bookcase.put(book.getId(),book);
        return "success";
    }

    @ApiOperation(value = "查询单本书籍",notes = "查询单本书籍",httpMethod = "GET",response = Book.class)
    @ApiImplicitParam(name = "id",value = "书籍id",required = true,paramType = "path",dataType = "String")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id")String id){
        return bookcase.get(id);
    }

    @ApiOperation(value = "修改书籍",notes = "修改书籍",httpMethod = "PUT",response = String.class)
    @PutMapping("/{id}")
    public String putBook(@PathVariable("id")String id,@RequestBody Book book){
        bookcase.put(id,book);
        return "success";
    }

    @ApiOperation(value = "删除书籍",notes = "删除书籍",httpMethod = "DELETE",response = String.class)
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")String id){
        bookcase.remove(id);
        return "success";
    }
}
