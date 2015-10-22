package ru.backup.domain;


import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskFromServerRepository extends JpaRepository<TaskFromServer, Long> {

//	List<TaskFromServer> findAllByUser(User user);
//	
//	List<TaskFromServer> findAllOrderByGeneralId(Long generalId);
//	
//	List<TaskFromServer> findAllByGeneralIdOrderByGeneralId(Long generalId);
}
