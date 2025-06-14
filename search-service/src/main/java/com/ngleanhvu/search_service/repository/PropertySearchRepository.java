package com.ngleanhvu.search_service.repository;

import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PropertySearchRepository {
   Page<PropertyDocument> searchProperties(PropertySearchDto propertySearchDto, PagingSearch pagingSearch);
}
