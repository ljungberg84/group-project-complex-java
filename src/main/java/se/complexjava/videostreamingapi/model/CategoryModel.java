package se.complexjava.videostreamingapi.model;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.Category;
import se.complexjava.videostreamingapi.entity.Video;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    private long id;

    @NotEmpty(message = "name can't be null or empty")
    private String name;

    private Set<Video> videos;

    private static ModelMapper modelMapper = new ModelMapper();

    public static CategoryModel fromEntity(Category entity){
        return modelMapper.map(entity, CategoryModel.class);
    }

    public static List<CategoryModel> fromEntity(Iterable<Category> entities){

        List<CategoryModel> models = new ArrayList<>();
        for (Category entity : entities) {
            models.add(fromEntity(entity));
        }

        return models;
    }
}
