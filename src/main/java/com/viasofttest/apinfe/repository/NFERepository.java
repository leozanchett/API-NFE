package com.viasofttest.apinfe.repository;

import com.viasofttest.apinfe.entity.NFE;
import com.viasofttest.apinfe.entity.UnavailableServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NFERepository extends JpaRepository<NFE, Long> {
    @Query(value = "select \n"+
                   "    n.*\n"+
                   "from \n"+
                   "    tb_nfestatus n\n"+
                   "order by n.id desc limit 15", nativeQuery = true)
    List<NFE> findByLastStatus();

    List<NFE> findAllByAutorizador(String uf);

    @Query(value = "select \n"+
                    "   *\n"+
                    "from\n"+
                    "   tb_nfestatus\n"+
                    "where\n"+
                    "   date_format(data, '%d-%m-%Y') =:data", nativeQuery = true)
    List<NFE> findAllByData(@Param(value = "data") String data);

    @Query(value = "select \n" +
            "    autorizador, count(*) as ocorrencias\n" +
            "FROM\n" +
            "    tb_nfestatus nfe\n" +
            "WHERE\n" +
            "    nfe.status = 'INDISPONIVEL'\n" +
            "group bY nfe.status , nfe.autorizador\n" +
            "order by count(*) desc\n" +
            "limit 1;", nativeQuery = true)
    String findByUnavailable();


}
