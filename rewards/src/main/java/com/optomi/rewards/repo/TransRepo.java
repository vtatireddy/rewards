package com.optomi.rewards.repo;

import com.optomi.rewards.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface TransRepo extends JpaRepository< Transactions,Long> {

    @Query("SELECT t FROM Transactions t WHERE t.transDTM >= :transDTM ")
    Collection<Transactions> findLastThreeMonthTrans(@Param("transDTM") LocalDate transDTM);

    @Query("SELECT t FROM Transactions t WHERE t.transDTM >= :transDTM  and t.customer.id = :custId ")
    Collection<Transactions> findLastThreeMonthTransByCustId(@Param("transDTM") LocalDate transDTM, @Param("custId") Long custId);
}
