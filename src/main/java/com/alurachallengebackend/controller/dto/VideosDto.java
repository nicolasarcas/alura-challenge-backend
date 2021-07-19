package com.alurachallengebackend.controller.dto;

import com.alurachallengebackend.model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideosDto {

    private Long id;
    private String titulo;
    private String url;
    private String descricao;

    public VideosDto(Video video){
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.url = video.getUrl();
        this.descricao = video.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<VideosDto> converter(List<Video> videos){
        return videos.stream().map(VideosDto::new).collect(Collectors.toList());
    }
}
