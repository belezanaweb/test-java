package br.com.blz.testjava.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseController {

    private static final String PARAM_PAGE = "page";

    private static final String PARAM_SIZE = "size";

    @SuppressWarnings("unchecked")
    protected <T> ResponseEntity<T> createResponse(T instance) {
        if (instance == null || (instance instanceof Collection && ((Collection<T>) instance).isEmpty())) {
            return new ResponseEntity<T>(NO_CONTENT);
        }
        return new ResponseEntity<T>(instance, OK);
    }

    protected <T> PagedResources<T> buildPagedResources(Page<T> page, String... params) {
        List<Link> links = new ArrayList<>();
        if (page.hasPrevious()) {
            links.add(paginableLinkBuilder(page.previousPageable(), Link.REL_PREVIOUS, params));
        }

        if (page.hasContent()) {
            links.add(paginableLinkBuilder(page.getNumber(), page.getSize(), Link.REL_SELF, params));
        }

        if (page.hasNext()) {
            links.add(paginableLinkBuilder(page.nextPageable(), Link.REL_NEXT, params));
        }

        PageMetadata pageMetadata = new PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(),
                page.getTotalPages());
        return new PagedResources<>(page.getContent(), pageMetadata, links);
    }

    protected Link paginableLinkBuilder(int page, int size, String rel, String... params) {
        return paginableLinkBuilder(new PageRequest(page, size), rel, params);
    }

    protected Link paginableLinkBuilder(Pageable pageRequest, String rel, String... params) {
        UriComponentsBuilder uriBuilder = fromCurrentRequestUri();
        Stream.of(params).forEach(param -> uriBuilder.query(param));
        uriBuilder.queryParam(PARAM_SIZE, pageRequest.getPageSize()).queryParam(PARAM_PAGE,
                pageRequest.getPageNumber());
        return new Link(uriBuilder.build().toUriString(), rel);
    }

}
