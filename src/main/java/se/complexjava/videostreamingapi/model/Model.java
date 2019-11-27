package se.complexjava.videostreamingapi.model;

import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.BaseEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public abstract class Model {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T extends Model, E extends BaseEntity> T fromEntity (E entity, Type target){

        return modelMapper.map(entity, target);
    }


    public static <T extends Model, E extends BaseEntity> List<T> fromEntity (Iterable<E> entities, Type target){

        List<T> models = new ArrayList<>();
        entities.forEach( entity -> models.add(modelMapper.map(entity, target)));

        return models;
    }
}
