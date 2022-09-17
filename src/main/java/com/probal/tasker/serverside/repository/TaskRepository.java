package com.probal.tasker.serverside.repository;

import com.probal.tasker.serverside.dto.CountStatus;
import com.probal.tasker.serverside.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByDueDateAsc();

    @Query(value = "select count(*)/(select count(*) from task) * 100, status from task group by status", nativeQuery = true)
    List<Object[]> getTaskPercentageGroupByStatus();
}
