package com.ngleanhvu.search_service.service.impl;

import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import com.ngleanhvu.search_service.repository.PropertySearchRepository;
import com.ngleanhvu.search_service.service.PropertySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertySearchServiceImpl implements PropertySearchService {

    private final PropertySearchRepository propertySearchRepository;

    @Override
    public Page<PropertyDocument> search(PropertySearchDto propertySearchDto, PagingSearch pagingSearch) {
        return propertySearchRepository.searchProperties(propertySearchDto, pagingSearch);
    }
}
