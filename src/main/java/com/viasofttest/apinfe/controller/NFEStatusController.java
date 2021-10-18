package com.viasofttest.apinfe.controller;

import com.viasofttest.apinfe.entity.NFE;
import com.viasofttest.apinfe.jsoup.PortalNFE;
import com.viasofttest.apinfe.repository.NFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/nfes")
public class NFEStatusController {

    @Autowired
    NFERepository nfeRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(nfeRepository.findAll());
    }

    @GetMapping("/gravarnfes")
    public List<NFE> gravarNFEs() throws IOException {
        return PortalNFE.getStatusServicos();
    }

    @GetMapping("/statusatual")
    public ResponseEntity<?> findByLastStatus() {
        return ResponseEntity.ok().body(nfeRepository.findByLastStatus());
    }

    @GetMapping("/{uf}")
    public ResponseEntity<?> findByUF(@PathVariable String uf) {
        return ResponseEntity.ok().body(nfeRepository.findAllByAutorizador(uf));
    }

    @GetMapping("/unavailable")
    public ResponseEntity<?> findByUnavailable(){
        return ResponseEntity.ok().body(nfeRepository.findByUnavailable());
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<?> findByData(@PathVariable String data) {
        return ResponseEntity.ok().body(nfeRepository.findAllByData(data));
    }

}
