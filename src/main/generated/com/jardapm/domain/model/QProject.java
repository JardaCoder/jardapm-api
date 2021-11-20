package com.jardapm.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1776158544L;

    public static final QProject project = new QProject("project");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DatePath<java.time.LocalDate> finalDate = createDate("finalDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DatePath<java.time.LocalDate> inclusionDate = _super.inclusionDate;

    public final DatePath<java.time.LocalDate> initialDate = createDate("initialDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModification = _super.lastModification;

    public final StringPath name = createString("name");

    //inherited
    public final EnumPath<com.jardapm.domain.enums.Situation> situation = _super.situation;

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

