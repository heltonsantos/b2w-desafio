package com.b2w.totalvendas.cache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheTotalvendasRepository extends JpaRepository<CacheTotalvendas, Long>{

}
