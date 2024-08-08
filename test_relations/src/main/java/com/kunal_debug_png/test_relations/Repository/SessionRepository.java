package com.kunal_debug_png.test_relations.Repository;

import com.kunal_debug_png.test_relations.Entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session,Long> {
    @Modifying
    @Query("DELETE FROM Session s WHERE s.userId = :userId")
    void deleteByUserId(Long userId);

}
