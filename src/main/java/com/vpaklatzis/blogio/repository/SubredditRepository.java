package com.vpaklatzis.blogio.repository;

import com.vpaklatzis.blogio.model.SubredditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<SubredditEntity, Long> {
    Optional<SubredditEntity> findByName(String subreddit);
}
