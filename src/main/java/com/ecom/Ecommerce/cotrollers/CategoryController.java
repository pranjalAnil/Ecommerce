package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.payloads.CategoryDto;
import com.ecom.Ecommerce.services.CategoryService;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.OK);

    }
    @PutMapping("{categoryId}/update")
    public ResponseEntity<?> updateCategory(@PathVariable int categoryId,@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.updateCategory(categoryId,categoryDto),HttpStatus.OK);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}/deleteCategory")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId){
        return new ResponseEntity<>(categoryService.deleteCategoryId(categoryId),HttpStatus.OK);
    }
}
