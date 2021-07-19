package com.alurachallengebackend.controller;

import com.alurachallengebackend.controller.dto.VideosDto;
import com.alurachallengebackend.controller.form.VideosForm;
import com.alurachallengebackend.model.Video;
import com.alurachallengebackend.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosRepository videosRepository;

    @GetMapping
    public List<Video> listarVideos(){
        return videosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideosDto> listarVideo(@PathVariable Long id){

        Optional<Video> video = videosRepository.findById(id);

        return video.map(value -> ResponseEntity.ok(new VideosDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideosDto> postarVideo(@RequestBody @Valid VideosForm form, UriComponentsBuilder uriBuilder){
        Video video = form.converter(videosRepository);
        videosRepository.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideosDto(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideosDto> atualizarVideo(@PathVariable Long id, @RequestBody @Valid VideosForm form){

        Optional<Video> optional = videosRepository.findById(id);
        if(optional.isPresent()){
            Video video = form.atualizar(id, videosRepository);
            return ResponseEntity.ok(new VideosDto(video));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerVideo(@PathVariable Long id){
        Optional<Video> optional = videosRepository.findById(id);
        if(optional.isPresent()){
            videosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
