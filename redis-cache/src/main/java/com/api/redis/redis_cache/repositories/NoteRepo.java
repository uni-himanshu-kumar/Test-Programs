package com.api.redis.redis_cache.repositories;

import com.api.redis.redis_cache.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note,String> {
}
