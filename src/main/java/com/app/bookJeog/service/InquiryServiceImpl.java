package com.app.bookJeog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class InquiryServiceImpl implements InquiryService{

}
