package com.wjb.mapper;

import com.wjb.base.BaseMapper;
import com.wjb.model.Offer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OfferMapper extends BaseMapper<Offer,Long>{


    List<Offer> list(@Param(value = "pageNum")Integer pageNum,@Param(value = "size")Integer size);

    List<Offer> getOffer(@Param(value = "pageNum")Integer pageNum,@Param(value = "size")Integer size);

    Integer count();
}