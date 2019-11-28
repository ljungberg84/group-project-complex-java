package se.complexjava.videostreamingapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity implements Serializable {

}
