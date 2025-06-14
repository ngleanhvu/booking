package com.ngleanhvu.search_service.repository.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import com.ngleanhvu.search_service.repository.PropertySearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PropertySearchRepositoryImpl implements PropertySearchRepository {

    private final ElasticsearchOperations operations;

    @Override
    public Page<PropertyDocument> searchProperties(PropertySearchDto dto, PagingSearch paging) {
        Query query = Query.of(q -> q.bool(b -> {
            if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
                b.must(m -> m.match(t -> t.field("title").query(FieldValue.of(dto.getTitle()))));
            }
            if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
                b.must(m -> m.match(t -> t.field("description").query(FieldValue.of(dto.getDescription()))));
            }
            if (dto.getPropertyType() != null && !dto.getPropertyType().isBlank()) {
                b.must(m -> m.term(t -> t.field("propertyType.id").value(dto.getPropertyType())));
            }
            if (dto.getRoomType() != null && !dto.getRoomType().isBlank()) {
                b.must(m -> m.term(t -> t.field("roomType.id").value(dto.getRoomType())));
            }
            if (dto.getCountry() != null && !dto.getCountry().isBlank()) {
                b.must(m -> m.term(t -> t.field("country.id").value(dto.getCountry())));
            }
            if (dto.getCity() != null && !dto.getCity().isBlank()) {
                b.must(m -> m.term(t -> t.field("city.id").value(dto.getCity())));
            }
            if (dto.getDistrict() != null && !dto.getDistrict().isBlank()) {
                b.must(m -> m.term(t -> t.field("district.id").value(dto.getDistrict())));
            }
            if (dto.getWard() != null && !dto.getWard().isBlank()) {
                b.must(m -> m.term(t -> t.field("ward.id").value(dto.getWard())));
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
