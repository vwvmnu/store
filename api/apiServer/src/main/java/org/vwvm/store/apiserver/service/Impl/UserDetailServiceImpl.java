package org.vwvm.store.apiserver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import jakarta.persistence.criteria.Predicate;
import org.springframework.util.ObjectUtils;
import org.vwvm.store.apiserver.entity.UserDetail;
import org.vwvm.store.apiserver.param.UserDetailParam;
import org.vwvm.store.apiserver.repository.UserDetailRepository;
import org.vwvm.store.apiserver.service.UserDetailService;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailServiceImpl implements UserDetailService {


    private final UserDetailRepository userDetailRepository;
    @Autowired
    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable){

        return userDetailRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //equal 示例

            if (!ObjectUtils.isEmpty(detailParam.getIntroduction())){
                predicates.add(cb.equal(root.get("introduction"),detailParam.getIntroduction()));
            }
            //like 示例
            if (!ObjectUtils.isEmpty(detailParam.getRealName())){
                predicates.add(cb.like(root.get("realName"),"%"+detailParam.getRealName()+"%"));
            }
            //between 示例
            if (detailParam.getMinAge()!=null && detailParam.getMaxAge()!=null) {
                Predicate agePredicate = cb.between(root.get("age"), detailParam.getMinAge(), detailParam.getMaxAge());
                predicates.add(agePredicate);
            }
            //greaterThan 大于等于示例
            if (detailParam.getMinAge()!=null){
                predicates.add(cb.greaterThan(root.get("age"),detailParam.getMinAge()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);

    }
}