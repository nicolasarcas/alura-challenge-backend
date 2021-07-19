package com.alurachallengebackend.controller.form;

import com.alurachallengebackend.model.Video;
import com.alurachallengebackend.repository.VideosRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VideosForm {

    @NotNull @NotEmpty @Size(max = 30)
    private String titulo;

    @NotNull @NotEmpty @Size(max = 15)
    private String url;

    @NotNull @NotEmpty @Size(max = 30)
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Video converter(VideosRepository repository){
        return new Video(
            this.titulo,
            this.url,
            this.descricao
        );
    }

    public Video atualizar(Long id, VideosRepository repository){
        Video video = repository.getOne(id);
        video.setTitulo(this.titulo);
        video.setUrl(this.url);
        video.setDescricao(this.descricao);

        return video;
    }
}
