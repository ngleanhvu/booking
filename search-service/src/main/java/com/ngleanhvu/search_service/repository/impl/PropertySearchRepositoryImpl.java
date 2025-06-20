package com.ngleanhvu.search_service.repository.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertySearchRepositoryImpl {

    private final ElasticsearchOperations operations;

    public Page<PropertyDocument> searchProperties(PropertySearchDto dto, PagingSearch paging) {
        Query query = Query.of(q -> q.bool(b -> {
            if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
                b.must(m -> m.match(t -> t.field("title").query(FieldValue.of(dto.getTitle()))));
            }
            if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
                b.must(m -> m.match(t -> t.field("description").query(FieldValue.of(dto.getDescription()))));
            }
            if (dto.getPropertyType() != null) {
                b.must(m -> m.term(t -> t.field("propertyType").value(dto.getPropertyType())));
            }
            if (dto.getRoomType() != null) {
                b.must(m -> m.term(t -> t.field("roomType").value(dto.getRoomType())));
            }
            if (dto.getCountry() != null) {
                b.must(m -> m.term(t -> t.field("country").value(dto.getCountry())));
            }
            if (dto.getCity() != null) {
                b.must(m -> m.term(t -> t.field("city").value(dto.getCity())));
            }
            if (dto.getDistrict() != null) {
                b.must(m -> m.term(t -> t.field("district").value(dto.getDistrict())));
            }
            if (dto.getWard() != null) {
                b.must(m -> m.term(t -> t.field("ward").value(dto.getWard())));
            }
            if (dto.getPrice() != null) {
                b.must(m -> m.range(r -> r
                        .field("pricePerNight")
                        .lte(JsonData.of(dto.getPrice().doubleValue()))
                ));
            }
            if (dto.getNumBeds() != null) {
                b.must(m -> m.term(t -> t.field("numBeds").value(dto.getNumBeds())));
            }
            if (dto.getNumBaths() != null) {
                b.must(m -> m.term(t -> t.field("numBathrooms").value(dto.getNumBaths())));
            }
            return b;
        }));

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .withPageable(paging.toPageable())
                .build();

        SearchHits<PropertyDocument> hits = operations.search(nativeQuery, PropertyDocument.class);
        return SearchHitSupport.searchPageFor(hits, nativeQuery.getPageable()).map(SearchHit::getContent);
    }

}
