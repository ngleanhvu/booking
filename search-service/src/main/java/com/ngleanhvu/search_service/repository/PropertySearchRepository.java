package com.ngleanhvu.search_service.repository;

import com.ngleanhvu.search_service.document.PropertyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PropertySearchRepository extends ElasticsearchRepository<PropertyDocument, Integer> {
}
