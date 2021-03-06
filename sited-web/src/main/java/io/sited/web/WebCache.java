package io.sited.web;

import io.sited.resource.Resource;

import java.util.Optional;

/**
 * @author chi
 */
public interface WebCache {
    Optional<Resource> get(String path);

    void create(Resource resource);

    void delete(String path);
}
