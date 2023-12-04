package com.example.api.repo;


import com.example.api.model.data.RsaGrowth;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RsaGrowthRepository extends CrudRepository<RsaGrowth, Long> {

    @Procedure("CPL_FETCH_MOBILEAPP_DATA")
    RsaGrowth fetchMobileAppData(@Param("param") String param);

//    @Query(value = "EXEC CPL_FETCH_MOBILEAPP_DATA :param", nativeQuery = true)
//    RsaGrowth fetchMobileAppData(@Param("param") String param);
}
