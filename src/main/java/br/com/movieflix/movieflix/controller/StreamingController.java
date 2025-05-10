package br.com.movieflix.movieflix.controller;


import br.com.movieflix.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.movieflix.entity.Streaming;
import br.com.movieflix.movieflix.mapper.StreamingMapper;
import br.com.movieflix.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> categories = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedCategory = streamingService.saveStreaming(newStreaming);
        return  ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.deleteStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
