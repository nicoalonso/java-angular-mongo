package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.identity.IdentityRepository;
import com.lacedorium.library.domain.identity.list.*;
import com.lacedorium.library.infrastructure.persistence.mongo.repository.exception.IlegalMapException;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.regex.Pattern;

public class MongoRepository<T, U> implements IdentityRepository<T> {
    private final MongoTemplate client;
    private final Class<T> domainClass;
    private final Class<U> entityClass;
    private final DomainEntityMapping<T, U> mapping;

    public MongoRepository(MongoTemplate client, Class<T> domainClass, Class<U> entityClass) {
        this.client = client;
        this.domainClass = domainClass;
        this.entityClass = entityClass;
        this.mapping = new DomainEntityMapping<>(domainClass, entityClass);
    }

    @Override
    public Optional<T> obtainById(String id) {
        U entity = client.findById(id, entityClass);
        return mapping.toDomain(entity);
    }

    @Override
    public void save(T domain) {
        if (domain == null) {
            throw new IllegalArgumentException("Domain object cannot be null");
        }

        U entity = mapping.toEntity(domain)
                .orElseThrow(() -> new IlegalMapException(domainClass, entityClass));
        client.save(entity);
    }

    @Override
    public void remove(String id) {
        U entity = client.findById(id, entityClass);
        if (entity == null) {
            return;
        }
        client.remove(entity);
    }

    public ListResult<T> obtainByQuery(@NonNull ListQuery query) {
        Query mongoQuery = new Query();
        makeWhere(mongoQuery, query);

        long count = client.count(mongoQuery, entityClass);

        makeSort(mongoQuery, query);
        mongoQuery.skip(query.offset());
        mongoQuery.limit(query.getLimit());

        List<T> items = client.find(mongoQuery, entityClass).stream()
                .map(mapping::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        Pagination pagination = new Pagination(count, query.getPage(), query.getLimit());

        return new ListResult<>(items, pagination);
    }

    protected void makeWhere(Query mongoQuery, @NonNull ListQuery query) {
        if (!query.hasFilters()) {
            return;
        }

        for (FilterField filter : query.getFilters()) {
            if (!filter.hasValue()) {
                continue;
            }

            Criteria criteria = switch (filter.getType()) {
                case FilterType.WILDCARD, FilterType.FUZZY -> {
                    String raw = String.valueOf(filter.getValue()).trim();
                    String regex = ".*" + Pattern.quote(raw) + ".*";
                    yield Criteria.where(filter.getName()).regex(regex, "i");
                }
                case FilterType.RANGE -> rangeFilter(filter);
                case FilterType.IN -> Criteria.where(filter.getName()).in(filter.getValue());
                case FilterType.ALL -> Criteria.where(filter.getName()).all(filter.getValue());
                case FilterType.EXISTS -> Criteria.where(filter.getName()).exists(filter.getValue() instanceof Boolean b && b);
                default -> Criteria.where(filter.getName()).is(filter.getValue());
            };

            if (criteria != null) {
                mongoQuery.addCriteria(criteria);
            }
        }
    }

    protected Criteria rangeFilter(@NonNull FilterField field) {
        if (!field.isRange() || !field.hasValue()) {
            return null;
        }

        Criteria criteria = Criteria.where(field.getName());
        FilterRange range = (FilterRange) field.getValue();

        if (range.hasFrom()) {
            criteria.gte(range.getFrom());
        }

        if (range.hasTo()) {
            criteria.lte(range.getTo());
        }

        return criteria;
    }

    protected void makeSort(Query mongoQuery, @NonNull ListQuery query) {
        if (!query.hasSort()) {
            return;
        }

        for (SortField sortField : query.getSort()) {
            Sort.Direction direction = sortField.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            mongoQuery.with(Sort.by(direction, sortField.getName()));
        }
    }

    protected Optional<T> findOneBy(@NonNull HashMap<String, Object> params) {
        Query query = new Query();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        U entity = client.findOne(query, entityClass);
        return mapping.toDomain(entity);
    }

    protected List<T> findBy(HashMap<String, Object> params) {
        return findBy(params, 0);
    }

    protected List<T> findBy(@NonNull HashMap<String, Object> params, int limit) {
        Query query = new Query();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() instanceof Criteria) {
                query.addCriteria((Criteria) entry.getValue());
                continue;
            }

            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        if (limit > 0) {
            query.limit(limit);
        }

        return client.find(query, entityClass).stream()
                .map(mapping::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
