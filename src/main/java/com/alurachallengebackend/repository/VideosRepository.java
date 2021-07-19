package com.alurachallengebackend.repository;

import com.alurachallengebackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Video, Long> {
}
