package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.model.CategoryModel;
import se.complexjava.videostreamingapi.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory (@Valid @RequestBody CategoryModel category) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryModel> getCategory (@PathVariable Long categoryId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<Iterable<CategoryModel>> getCategories() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteUser(@PathVariable Long categoryId) throws Exception {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity updateUser(@PathVariable long categoryId, @Valid @RequestBody CategoryModel category) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(category, categoryId));
    }
}
