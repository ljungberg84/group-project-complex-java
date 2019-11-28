package se.complexjava.videostreamingapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.BaseEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class Model {

    private Long id;

    private static ModelMapper modelMapper = new ModelMapper();


    public static <T extends Model, E extends BaseEntity> T fromEntity (E entity, Type target){

        modelMapper.typeMap(entity.getClass(), UserModel.class).addMappings(mp -> mp.skip( UserModel::setPassword));

        return modelMapper.map(entity, target);
    }


    public static <T extends Model, E extends BaseEntity> List<T> fromEntity (Iterable<E> entities, Type target){

        List<T> models = new ArrayList<>();
        entities.forEach( entity -> models.add(fromEntity(entity, target )));

        return models;
    }
}
