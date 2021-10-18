package com.viasofttest.apinfe.job;

import com.viasofttest.apinfe.feign.DataNFE;
import com.viasofttest.apinfe.repository.NFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@EnableAsync
@EnableScheduling
public class GETRefresh {

    @Autowired
    private NFERepository nfeRepository;

    @Autowired
    private DataNFE dataNFE;

    @Transactional
    @Scheduled(fixedRate = 300000)
    private void findDataNFE(){
        try {
            nfeRepository.saveAll(dataNFE.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
