package com.ngleanhvu.search_service.service;

import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import org.springframework.data.domain.Page;

public interface PropertySearchService {
    Page<PropertyDocument> search(PropertySearchDto propertySearchDto, PagingSearch pagingSearch);
}
