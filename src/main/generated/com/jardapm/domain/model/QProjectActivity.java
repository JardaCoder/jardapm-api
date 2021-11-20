package com.jardapm.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectActivity is a Querydsl query type for ProjectActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProjectActivity extends EntityPathBase<ProjectActivity> {

    private static final long serialVersionUID = -2123362689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectActivity projectActivity = new QProjectActivity("projectActivity");

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

    public final QProject project;

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    //inherited
    public final EnumPath<com.jardapm.domain.enums.Situation> situation = _super.situation;

    public QProjectActivity(String variable) {
        this(ProjectActivity.class, forVariable(variable), INITS);
    }

    public QProjectActivity(Path<? extends ProjectActivity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectActivity(PathMetadata metadata, PathInits inits) {
        this(ProjectActivity.class, metadata, inits);
    }

    public QProjectActivity(Class<? extends ProjectActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

