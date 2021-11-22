package com.jardapm.domain.repository.custom;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jardapm.domain.dto.ProjectDto;
import com.jardapm.domain.enums.YesNo;
import com.jardapm.domain.model.Project;
import com.jardapm.domain.model.ProjectActivity;
import com.jardapm.domain.model.QProject;
import com.jardapm.domain.model.QProjectActivity;
import com.jardapm.domain.repository.custom.interfaces.ProjectRepositoryCustomInterface;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustomInterface {

	public ProjectRepositoryImpl() {
		super(Project.class);
	}

	@PersistenceContext
	private EntityManager entityManager;

	QProject qProject = QProject.project;
	QProjectActivity qProjectActivity = QProjectActivity.projectActivity;

	@Override
	public List<ProjectDto> listProjects(BooleanExpression booleanExpression) {

		JPQLQuery<ProjectDto> query = new JPAQuery<ProjectDto>(entityManager);

	
		query.select(Projections.constructor(ProjectDto.class,
				qProject.name,
				qProject.id,
				qProject.initialDate,
				qProject.finalDate
		));

		query.from(qProject);
		query.orderBy(qProject.finalDate.desc()).distinct();

		if (booleanExpression != null) {
			query.where(booleanExpression);
		}

		List<ProjectDto> projects = query.fetch();

		projects.forEach(project -> {
			project = getAllInformations(project);
		});

		return projects;
	}

	public List<ProjectActivity> getProjectActivitiesListById(Long projectId) {

		if (projectId != null) {
			JPQLQuery<ProjectActivity> query = new JPAQuery<ProjectActivity>(entityManager);

			query.select(qProjectActivity).from(qProjectActivity).where(qProjectActivity.projectId.eq(projectId))
					.orderBy(qProjectActivity.finalDate.desc());

			return query.fetch();
		}

		return null;
	}
	
	public Double getPercentageProgressById(Long projectId) {

		if (projectId != null) {
			JPQLQuery<Double> query = new JPAQuery<Double>(entityManager);
			
			NumberExpression<Double> doneActivities = new CaseBuilder().when(qProjectActivity.finished.eq(YesNo.YES))
					.then(Double.valueOf(1)).otherwise(Double.valueOf(0)).sum();

			query.select(MathExpressions.round(doneActivities.multiply(100).divide(qProjectActivity.count()), 2))
				.from(qProjectActivity)
				.where(qProjectActivity.projectId.eq(projectId));
			
			var percentage = query.fetchOne();

			return percentage != null ? percentage : 0;
		}

		return null;
	}
	
	/**
	 * 
	 * Pega as atividades, porcentagem de progresso e verifica se o projeto pode estar em atraso ou não.
	 *
	 * @param projects
	 * @return
	 */
	private ProjectDto getAllInformations(ProjectDto project){
		
		project.setProjectActivities(getProjectActivitiesListById(project.getId()));
		project.setPercentageProgress(getPercentageProgressById(project.getId()));

		if(project.getPercentageProgress() == 100) {
			project.setFinished(YesNo.YES);
		}
		
		var activities = project.getProjectActivities();
		
		project.setTotalActivities((long) activities.size());

		if (!activities.isEmpty()) {
			if (project.getFinalDate().isBefore(activities.get(0).getFinalDate())) {
				project.setLate(YesNo.YES);
			}
		}else{
			if (project.getFinalDate().isBefore(LocalDate.now())) {
				project.setLate(YesNo.YES);
			}
		}
	
		
		return project;
	}

}
