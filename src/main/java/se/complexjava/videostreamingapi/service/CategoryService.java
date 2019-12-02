package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.model.CategoryModel;

public interface CategoryService {
    CategoryModel createCategory(CategoryModel category) throws Exception;
    CategoryModel getCategory(Long categoryId) throws Exception;
    Iterable<CategoryModel> getCategories() throws Exception;
    void deleteCategory(Long categoryId) throws Exception;
    CategoryModel updateCategory(CategoryModel category, long categoryId) throws Exception;
}
