package com.viasofttest.apinfe.feign;

import com.viasofttest.apinfe.entity.NFE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "nfe", url = "http://localhost:8080/nfes/gravarnfes")
public interface DataNFE {

    @GetMapping
    List<NFE> get();
}
