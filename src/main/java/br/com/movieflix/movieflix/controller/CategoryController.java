package br.com.movieflix.movieflix.controller;

import br.com.movieflix.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.movieflix.entity.Category;
import br.com.movieflix.movieflix.mapper.CategoryMapper;
import br.com.movieflix.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

        private final CategoryService categoryService;

        @GetMapping()
        public ResponseEntity<List<CategoryResponse>> getAllCategories() {
            List<CategoryResponse> categories = categoryService.findAll()
                    .stream()
                    .map(CategoryMapper::toCategoryResponse)
                    .toList();

            return ResponseEntity.ok(categories);
        }

        @PostMapping()
        public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
            Category newCategory = CategoryMapper.toCategory(request);
            Category savedCategory = categoryService.saveCategory(newCategory);
            return  ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
        }

        @GetMapping("/{id}")
        public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id) {
            return categoryService.findById(id)
                    .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
