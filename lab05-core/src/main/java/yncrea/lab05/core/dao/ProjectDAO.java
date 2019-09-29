package yncrea.lab05.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yncrea.lab05.core.entity.Project;

public interface ProjectDAO extends JpaRepository<Project,Long> {

}
