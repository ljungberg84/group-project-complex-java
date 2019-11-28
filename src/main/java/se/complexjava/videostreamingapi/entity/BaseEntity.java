package se.complexjava.videostreamingapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private static ModelMapper modelMapper = new ModelMapper();


    public static <E extends BaseEntity, T extends Model> E fromModel (T model, Type target){

        return modelMapper.map(model, target);
    }


    public static <E extends BaseEntity, T extends Model> List<E> fromModel (Iterable<T> models, Type target){

        List<E> entities = new ArrayList<>();
        models.forEach( model -> entities.add(fromModel(model, target)));

        return entities;
    }
}
