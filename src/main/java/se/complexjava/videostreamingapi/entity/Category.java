package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.CategoryModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "name can't be null or empty")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Video> videos;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Category fromModel(CategoryModel model){
        return modelMapper.map(model, Category.class);
    }

    public static List<Category> fromModel(Iterable<CategoryModel> models){
        List<Category> entities = new ArrayList<>();
        for (CategoryModel model : models) {
            entities.add(fromModel(model));
        }
        return entities;
    }
}
