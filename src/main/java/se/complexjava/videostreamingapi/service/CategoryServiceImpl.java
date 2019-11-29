package se.complexjava.videostreamingapi.service;

import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.Category;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CategoryModel;
import se.complexjava.videostreamingapi.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryModel createCategory(CategoryModel category) throws Exception {
        Category categoryEntity = Category.fromModel(category);
        return CategoryModel.fromEntity(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryModel getCategory(Long categoryId) throws Exception {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(!category.isPresent()){
            throw new ResourceNotFoundException(String.format("Category with id: %s not found", categoryId));
        }

        return CategoryModel.fromEntity(category.get());
    }

    @Override
    public Iterable<CategoryModel> getCategories() throws Exception {
        return CategoryModel.fromEntity(categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Long categoryId) throws Exception {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel category, long categoryId) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(!optionalCategory.isPresent()){
            throw new ResourceNotFoundException(String.format("Category with id: %s not found", categoryId));
        }

        Category categoryToUpdate = optionalCategory.get();
        categoryToUpdate.setName(category.getName());

        return CategoryModel.fromEntity(categoryRepository.save(categoryToUpdate));
    }
}
