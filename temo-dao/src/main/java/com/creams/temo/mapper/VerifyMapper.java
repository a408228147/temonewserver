package com.creams.temo.mapper;

import com.creams.temo.entity.Verify;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VerifyMapper {

    List<Verify> queryVerify(@Param("case_id") String caseId);

    void addVerify(Verify verifyRequest);

    void updateVerifyById(Verify verifyRequest);

    @Delete("delete from verify where case_id = #{case_id}")
    void deleteVerify(@Param("case_id") String caseId);
}
