package com.vpaklatzis.blogio.repository;

import com.vpaklatzis.blogio.model.SubredditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<SubredditEntity, Long> {
}
